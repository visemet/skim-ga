package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface NumberBase<T extends Number> extends Base<T> {

    T getMinValue();

    void setMinValue(T minValue);

    T getMaxValue();

    void setMaxValue(T maxValue);
}
