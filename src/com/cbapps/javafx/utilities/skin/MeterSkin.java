package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator;
import com.cbapps.javafx.utilities.control.MeterView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 * @author Coen Boelhouwers
 */
public class MeterSkin extends SkinBase<MeterView> {

	/**
	 * Constructor for all SkinBase instances.
	 *
	 * @param control The control for which this Skin should attach to.
	 */
	public MeterSkin(MeterView control) {
		super(control);
		Pane pane = new Pane();
		Arc arc = new Arc(0, 0, 2, 2, 90 - control.getAngle()/2, control.getAngle());
		arc.lengthProperty().bind(control.angleProperty());
		arc.startAngleProperty().bind(Bindings.subtract(90, control.angleProperty().divide(2)));
		arc.radiusXProperty().bind(arc.radiusYProperty());
		arc.radiusYProperty().bind(Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.35));
		arc.centerXProperty().bind(pane.widthProperty().divide(2));
		arc.centerYProperty().bind(pane.heightProperty().divide(2));
		arc.setType(ArcType.OPEN);
		arc.setStrokeLineCap(StrokeLineCap.ROUND);
		arc.setStroke(Color.GREEN);
		arc.strokeWidthProperty().bind(arc.radiusYProperty().divide(10));
		arc.setFill(Color.TRANSPARENT);

		Label label = new Label("");
		label.textProperty().bind(control.nameProperty());
		label.setMaxWidth(60);
		label.setWrapText(true);
		label.setTextAlignment(TextAlignment.CENTER);
		label.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(label.widthProperty().divide(2)));
		label.layoutYProperty().bind(pane.heightProperty().divide(2).subtract(arc.radiusYProperty().divide(2))
				.subtract(label.heightProperty().divide(2)));
		label.scaleXProperty().bind(label.scaleYProperty());
		label.scaleYProperty().bind(arc.radiusYProperty().multiply(0.025));

		Label valueText = new Label();
		control.textFormatProperty().addListener((v1, v2, v3) ->
				valueText.textProperty().bind(Bindings.format(v3, control.valueProperty())));
		valueText.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(valueText.widthProperty().divide(2)));
		valueText.layoutYProperty().bind(pane.heightProperty().subtract(valueText.heightProperty())
				.subtract(valueText.heightProperty().multiply(valueText.scaleYProperty())
						.subtract(valueText.heightProperty()).divide(2)));
		valueText.scaleXProperty().bind(valueText.scaleYProperty());
		valueText.scaleYProperty().bind(arc.radiusYProperty().multiply(0.035));

		Path arrow = new Path(
				new MoveTo(110, 0),
				new LineTo(0, 10),
				new ArcTo(10, 10, 180, 0, -10, true, true),
				new LineTo(110, 0));
		arrow.layoutXProperty().bind(pane.widthProperty().divide(2));
		arrow.layoutYProperty().bind(pane.heightProperty().divide(2));
		arrow.setStroke(Color.TRANSPARENT);
		arrow.setFill(Color.GRAY);

		Scale sc = new Scale(1, 1, 0, 0);
		sc.xProperty().bind(sc.yProperty());
		sc.yProperty().bind(arc.radiusYProperty().divide(100));
		Rotate rt = new Rotate(-230, 0, 0);
		control.valueProperty().addListener((v1, v2, v3) -> {
			System.out.println("move to " + (arc.getStartAngle() + arc.getLength() -
					(v3.doubleValue() / (control.getMax() - control.getMin()) * arc.getLength())) + " deg");
				new Timeline(
						new KeyFrame(Duration.ZERO,
								new KeyValue(rt.angleProperty(), rt.getAngle())),
						new KeyFrame(Duration.millis(400),
								new KeyValue(rt.angleProperty(), arc.getStartAngle() + (arc.getLength() -
										(v3.doubleValue() / (control.getMax() - control.getMin()) * arc.getLength())),
										new SmoothInterpolator(SmoothInterpolator.AnimType.ACCELDECEL)))
				).playFromStart();}
		);
		arrow.getTransforms().addAll(rt, sc);

		pane.getChildren().addAll(label, valueText, arc, arrow);
		pane.setMinSize(75, 75);
		pane.setPrefSize(200, 200);
		getChildren().add(pane);
	}
}
