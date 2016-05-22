package com.casa.brunabaudel.testenodejs;

/**
 * Created by rafaelllins on 22/05/2016.
 */
public class ItemStickHeader<THeader, TItem> {
    private THeader header;
    private TItem item;

    public ItemStickHeader(TItem item) {
        this.item = item;
    }

    public ItemStickHeader(THeader header, TItem item) {
        this.header = header;
        this.item = item;
    }

    public THeader getHeader() {
        return header;
    }

    public void setHeader(THeader header) {
        this.header = header;
    }

    public TItem getItem() {
        return item;
    }

    public void setItem(TItem item) {
        this.item = item;
    }
}