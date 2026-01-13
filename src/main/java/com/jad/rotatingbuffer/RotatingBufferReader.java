package com.jad.rotatingbuffer;

class RotatingBufferReader<E> extends RotatingBufferActor<E> {

    RotatingBufferReader(final RotatingBuffer<E> rotatingBuffer) {
        super(rotatingBuffer);
    }

    public E read() {
        E result = this.getRotatingBuffer().getFromIndex(this.getIndex());
        this.incrementIndex();
        return result;
    }
}
