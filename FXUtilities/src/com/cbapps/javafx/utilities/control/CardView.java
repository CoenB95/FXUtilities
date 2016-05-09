package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.resources.RobotoFont;
import com.cbapps.javafx.utilities.skin.CardViewSkin;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class CardView extends Button {

	private final StringProperty cardText = 
			new SimpleStringProperty("Text");
	private final StringProperty cardTitle = 
			new SimpleStringProperty("Title");
	private final ObjectProperty<Font> cardTextFont =
			new SimpleObjectProperty<Font>(
					Font.loadFont(RobotoFont.regular(), 14));
	private final ObjectProperty<Font> cardTitleFont =
			new SimpleObjectProperty<Font>(
					Font.loadFont(RobotoFont.medium(), 16));
	
	public StringProperty cardTextProperty() {
		return cardText;
	}
	
	public ObjectProperty<Font> cardTextFontProperty() {
		return cardTextFont;
	}

	public StringProperty cardTitleProperty() {
		return cardTitle;
	}
	
	public ObjectProperty<Font> cardTitleFontProperty() {
		return cardTitleFont;
	}

	public CardView() {
		this.setGraphic(CardViewSkin.createSkin(this));
	}

	public String getCardText() {
		return textProperty().get();
	}

	public Font getCardTextFont() {
		return cardTextFontProperty().get();
	}
	
	public String getCardTitle() {
		return cardTitleProperty().get();
	}
	
	public Font getCardTitleFont() {
		return cardTitleFontProperty().get();
	}

	public void setCardText(String t) {
		cardTextProperty().set(t);
	}
	
	public void setCardTextFont(Font f) {
		cardTextFontProperty().set(f);
	}

	public void setCardTitle(String t) {
		cardTitleProperty().set(t);
	}
	
	public void setCardTitleFont(Font f) {
		cardTitleFontProperty().set(f);
	}
}
