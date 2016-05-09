package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.control.CardView;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;

public class CardViewSkin extends SkinBase<CardView> {
	
	public CardViewSkin(CardView control) {
		super(control);
		VBox box = new VBox();
		getSkinnable().setId("cardview");
		getSkinnable().getStylesheets().add("/com/cbapps/javafx/"
				+ "utilities/styles/CleanTheme.css");
		getSkinnable().onMouseClickedProperty().bind(control.onClickedProperty());
		box.setPadding(new Insets(5));
		Label titleLabel = new Label();
		VBox.setMargin(titleLabel, new Insets(5));
		titleLabel.fontProperty().bind(control.titleFontProperty());
		titleLabel.textProperty().bind(control.titleProperty());
		Label textLabel = new Label();
		VBox.setMargin(textLabel, new Insets(5));
		textLabel.fontProperty().bind(control.textFontProperty());
		textLabel.textProperty().bind(control.textProperty());
		box.getChildren().addAll(new Group(titleLabel), new Group(textLabel));
		getChildren().addAll(box);
	}
}
