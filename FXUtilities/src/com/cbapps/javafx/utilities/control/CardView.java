package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.resources.RobotoFont;
import com.cbapps.javafx.utilities.skin.CardViewSkin;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class CardView extends Control {

	private final StringProperty text = new SimpleStringProperty("Text");
	private final StringProperty title = new SimpleStringProperty("Title");
	private final ObjectProperty<EventHandler<? super MouseEvent>> listener = 
			new SimpleObjectProperty<>();
	private final ObjectProperty<Font> textFont =
			new SimpleObjectProperty<Font>(
					Font.loadFont(RobotoFont.regular(), 14));
	private final ObjectProperty<Font> titleFont =
			new SimpleObjectProperty<Font>(
					Font.loadFont(RobotoFont.medium(), 16));

	public StringProperty textProperty() {
		return text;
	}
	
	public ObjectProperty<Font> textFontProperty() {
		return textFont;
	}

	public StringProperty titleProperty() {
		return title;
	}
	
	public ObjectProperty<Font> titleFontProperty() {
		return titleFont;
	}

	public ObjectProperty<EventHandler<? super MouseEvent>> 
	onClickedProperty() {
		return listener;
	}

	public CardView() {

	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new CardViewSkin(this);
	}

	public EventHandler<? super MouseEvent> getOnClicked() {
		return listener.get();
	}

	public String getText() {
		return textProperty().get();
	}

	public Font getTextFont() {
		return textFontProperty().get();
	}
	
	public String getTitle() {
		return titleProperty().get();
	}
	
	public Font getTitleFont() {
		return titleFontProperty().get();
	}

	public void setOnClicked(EventHandler<? super MouseEvent> event) {
		listener.set(event);
	}

	public void setText(String t) {
		textProperty().set(t);
	}
	
	public void setTextFont(Font f) {
		textFontProperty().set(f);
	}

	public void setTitle(String t) {
		titleProperty().set(t);
	}
	
	public void setTitleFont(Font f) {
		titleFontProperty().set(f);
	}
}
