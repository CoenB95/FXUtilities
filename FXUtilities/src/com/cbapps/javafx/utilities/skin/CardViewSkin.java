package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.control.CardView;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CardViewSkin extends SkinBase<CardView> {
	
	public CardViewSkin(CardView control) {
		super(control);
		VBox box = new VBox();
		box.backgroundProperty().bind(Bindings.when(box.hoverProperty())
				.then(new Background(new BackgroundFill(Color.gray(0.9),
				new CornerRadii(2),null)))
				.otherwise(new Background(new BackgroundFill(Color.WHITE,
						new CornerRadii(2),null))));
		box.setEffect(new DropShadow());
		box.onMouseClickedProperty().bind(control.onClickedProperty());
		box.setPadding(new Insets(5));
		Label titleLabel = new Label();
		//titleLabel.setPadding(new Insets(5));
		VBox.setMargin(titleLabel, new Insets(5));
		//titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleLabel.setFont(Font.loadFont(CardViewSkin.class
				.getResourceAsStream("/com/cbapps/javafx/utilities/"
						+ "resources/Roboto-Medium.ttf"), 16));
		titleLabel.textProperty().bind(control.titleProperty());
		Label textLabel = new Label();
		//textLabel.setPadding(new Insets(5));
		VBox.setMargin(textLabel, new Insets(5));
		//textLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		textLabel.setFont(Font.loadFont(CardViewSkin.class
				.getResourceAsStream("/com/cbapps/javafx/utilities/"
						+ "resources/Roboto-Regular.ttf"), 14));
		textLabel.textProperty().bind(control.textProperty());
		box.getChildren().addAll(new Group(titleLabel), new Group(textLabel));
		getChildren().addAll(box);
	}
}
