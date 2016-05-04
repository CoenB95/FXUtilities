package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.CardViewSkin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Skin;

public class CardView extends ButtonBase {

	//private final StringProperty text = new SimpleStringProperty("Text");
	private final StringProperty title = new SimpleStringProperty("Title");
	
	//public StringProperty textProperty() {
	//	return text;
	//}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public CardView() {
		
	}
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new CardViewSkin(this);
	}
	
	//public String getText() {
	//	return textProperty().get();
	//}
	
	public String getTitle() {
		return titleProperty().get();
	}
	
	//public void setText(String t) {
	//	textProperty().set(t);
	//}
	
	public void setTitle(String t) {
		titleProperty().set(t);
	}

	@Override
	public void fire() {
		if (!isDisabled()) {
            fireEvent(new ActionEvent());
        }
	}
}
