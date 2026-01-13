package com.jad.rotatingbuffer;

public class RotatingBuffer<E> {

  private final E[] data;
  private RotatingBufferReader<E> reader;
  private RotatingBufferWriter<E> writer;

  private boolean empty;
  private final int size;

  @SuppressWarnings("unchecked")
  public RotatingBuffer(final int size) {
      this.size = Math.max(1, size);
      this.reset();
      this.data = (E[]) new Object[this.getSize()];
  }

  public final int getSize() {
      return this.size;
  }

  public final void reset() {
      this.empty = true;
      this.reader = new RotatingBufferReader<>(this);
      this.writer = new RotatingBufferWriter<>(this);
  }

  public final boolean isEmpty() {
      return this.empty;
  }

  public final boolean isFull() {
      return (this.getReaderIndex() == this.getWriterIndex()) && (!this.isEmpty());
  }

  int getReaderIndex() {
    return this.reader.getIndex();
  }

  public final synchronized E read() {
      if (isEmpty()) return null;
      E result = this.reader.read();
      if (this.getReaderIndex() == this.getWriterIndex()) this.empty = true;
      return result;
  }

  public final synchronized boolean write(final E data) {
      if (this.isFull()) return false;
      this.empty = false;
      return this.writer.write(data);
  }

  final int getWriterIndex() {
    return this.writer.getIndex();
  }

  public E getFromIndex(final int index) {
      if (index < 0 || index >= this.getSize()) throw new IndexOutOfBoundsException();
      return this.data[index];
  }

  public void setAtIndex(E data, int index) {
      if (index < 0 || index >= this.getSize()) throw new IndexOutOfBoundsException();
      this.data[index] = data;
  }
}
