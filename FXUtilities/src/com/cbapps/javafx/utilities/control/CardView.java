package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.CardViewSkin;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;

public class CardView extends Control {

	private final StringProperty text = new SimpleStringProperty("Text");
	private final StringProperty title = new SimpleStringProperty("Title");
	private final ObjectProperty<EventHandler<? super MouseEvent>> listener 
	= new SimpleObjectProperty<>();
	
	public StringProperty textProperty() {
		return text;
	}
	
	public StringProperty titleProperty() {
		return title;
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
	
	public String getTitle() {
		return titleProperty().get();
	}
	
	public void setOnClicked(EventHandler<? super MouseEvent> event) {
		listener.set(event);
	}
	
	public void setText(String t) {
		textProperty().set(t);
	}
	
	public void setTitle(String t) {
		titleProperty().set(t);
	}
}
