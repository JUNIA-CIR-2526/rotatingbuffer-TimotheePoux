package com.jad.rotatingbuffer;

public class RotatingBuffer<E> {

  private final E[] data;
  private final RotatingBufferReader<E> reader;
  private final RotatingBufferWriter<E> writer;

  private boolean empty = true;
  private final int size;

  @SuppressWarnings("unchecked")
  public RotatingBuffer(final int size) {
      this.size = size;

      this.data = (E[]) new Object[this.getSize()];

    // TODO: Continue the constructor implementation here You have to change the two lines below
    this.reader = new RotatingBufferReader<>();
    this.writer = new RotatingBufferWriter<>();
  }

  public final int getSize() {
      return size;
  }

  public final void reset() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public final boolean isEmpty() {
      return empty;
  }

  public final boolean isFull() {
      return getReaderIndex() == getWriterIndex() && !empty;
  }

  int getReaderIndex() {
    return this.reader.getIndex();
  }

  public final synchronized E read() {
      if (getReaderIndex() == getWriterIndex()){
          empty = true;
      }
      return this.reader.read();
  }

  public final synchronized boolean write(final E data) {
      empty = false;
      return this.writer.write(data);
  }

  final int getWriterIndex() {
    return this.writer.getIndex();
  }
}
