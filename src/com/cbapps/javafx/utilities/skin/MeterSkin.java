package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator;
import com.cbapps.javafx.utilities.control.MeterView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
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
		arc.typeProperty().bind(control.arcTypeProperty());
		arc.setStrokeLineCap(StrokeLineCap.ROUND);
		arc.strokeProperty().bind(control.arcStrokeProperty());
		arc.strokeWidthProperty().bind(arc.radiusYProperty().divide(10));
		arc.fillProperty().bind(control.arcFillProperty());

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
		ChangeListener<String> formatListener = (v1, v2, v3) -> {
			if (v3 == null) {
				valueText.setVisible(false);
			} else {
				valueText.textProperty().bind(Bindings.format(v3, control.valueProperty()));
				valueText.setVisible(true);
			}
		};
		formatListener.changed(null, null, control.getTextFormat());
		control.textFormatProperty().addListener(formatListener);
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
		arrow.strokeProperty().bind(control.arrowStrokeProperty());
		arrow.fillProperty().bind(control.arrowFillProperty());

		Scale sc = new Scale(1, 1, 0, 0);
		sc.xProperty().bind(sc.yProperty());
		sc.yProperty().bind(arc.radiusYProperty().divide(100));
		Rotate rt = new Rotate(0, 0, 0);
		ChangeListener<Number> valueListener = (v1, v2, v3) -> {
				new Timeline(
						new KeyFrame(Duration.ZERO,
								new KeyValue(rt.angleProperty(), rt.getAngle())),
						new KeyFrame(Duration.millis(400),
								new KeyValue(rt.angleProperty(), -arc.getStartAngle() - (arc.getLength() -
										(v3.doubleValue() / (control.getMax() - control.getMin()) * arc.getLength())),
										new SmoothInterpolator(SmoothInterpolator.AnimType.ACCELDECEL)))
				).playFromStart();
		};
		valueListener.changed(null, null, Double.isNaN(control.getValue()) ? 0 : control.getValue());
		control.valueProperty().addListener(valueListener);
		arrow.getTransforms().addAll(rt, sc);

		pane.getChildren().addAll(label, valueText, arc, arrow);
		pane.setMinSize(75, 75);
		pane.setPrefSize(200, 200);
		getChildren().add(pane);
	}
}
