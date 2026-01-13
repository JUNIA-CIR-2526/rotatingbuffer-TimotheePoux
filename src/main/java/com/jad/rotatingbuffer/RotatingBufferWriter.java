package com.jad.rotatingbuffer;

class RotatingBufferWriter<E> extends RotatingBufferActor<E> {
    RotatingBufferWriter(final RotatingBuffer<E> rotatingBuffer) {
        super(rotatingBuffer);
    }

    public boolean write(final E data) {
        this.getRotatingBuffer().setAtIndex(data, this.getIndex());
        this.incrementIndex();
        return true;
    }
}
