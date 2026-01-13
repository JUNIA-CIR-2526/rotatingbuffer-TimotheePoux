package com.jad.rotatingbuffer;

abstract class RotatingBufferActor<E> {
    private int index = 0;
    private final RotatingBuffer<E> rotatingBuffer;

    RotatingBufferActor(RotatingBuffer<E> rotatingBuffer) {
        this.rotatingBuffer = rotatingBuffer;
    }

    public RotatingBuffer<E> getRotatingBuffer() {
        return rotatingBuffer;
    }

    int getIndex() {
    return this.index;
  }

    protected void incrementIndex() {
        this.index = (this.index + 1) % this.rotatingBuffer.getSize();
    }
}
