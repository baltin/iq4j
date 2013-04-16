package com.iq4j.utils.model;

public abstract class EditableRangeImpl<T extends Comparable<T>> extends RangeImpl<T> implements EditableRange<T> {

	private static final long serialVersionUID = 7503664628775263820L;

	public EditableRangeImpl (T min, T max) {
		setMin(min);
		setMax(max);
	}
}
