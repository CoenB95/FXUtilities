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
		getChildren().addAll(createSkin(control));
	}
	
	public static VBox createSkin(CardView control) {
		VBox box = new VBox();
		control.setId("cardview");
		control.getStylesheets().add("/com/cbapps/javafx/"
				+ "utilities/styles/CleanTheme.css");
		box.setPadding(new Insets(5));
		Label titleLabel = new Label();
		VBox.setMargin(titleLabel, new Insets(5));
		titleLabel.fontProperty().bind(control.cardTitleFontProperty());
		titleLabel.textProperty().bind(control.cardTitleProperty());
		Label textLabel = new Label();
		VBox.setMargin(textLabel, new Insets(5));
		textLabel.fontProperty().bind(control.cardTextFontProperty());
		textLabel.textProperty().bind(control.cardTextProperty());
		box.getChildren().addAll(new Group(titleLabel), 
				new Group(textLabel));
		return box;
	}
}
