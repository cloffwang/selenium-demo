package com.cliff.runner;

import io.cucumber.testng.Pickle;
import io.cucumber.testng.PickleWrapper;

import java.awt.image.PixelGrabber;

class PickerWithData implements PickleWrapper {
    private final Pickle pickle;
    private final PickleWrapper pickleWrapper;
    private final BrowserData browserData;

    PickerWithData(PickleWrapper pickleWrapper, BrowserData browserData) {
        this.pickle = pickleWrapper.getPickle();
        this.pickleWrapper = pickleWrapper;
        this.browserData = browserData;
    }

    @Override
    public Pickle getPickle() {
        return pickle;
    }

    public BrowserData getBrowserData() {
        return browserData;
    }

    public PickleWrapper getPickleWrapper() {
        return pickleWrapper;
    }
}
