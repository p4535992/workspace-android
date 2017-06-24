package com.example.tenti.apobusmy;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is the class that contains the information related to the BusStop
 */
public final class BusStop implements Parcelable /* Serializable */ {

  /**
   * Constant that identify an existing data
   */
  private static final byte PRESENT = 1;

  /**
   * Constant that identify a NON existing data
   */
  private static final byte NOT_PRESENT = 0;

  /**
   * Implementation of a CREATOR for the creation of the instance
   */
  public static final Parcelable.Creator<BusStop> CREATOR = new Parcelable.Creator<BusStop>() {
    public BusStop createFromParcel(Parcel in) {
      return new BusStop(in);
    }

    public BusStop[] newArray(int size) {
      return new BusStop[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
    if (direction != null) {
      dest.writeByte(PRESENT);
      dest.writeString(direction);
    } else {
      dest.writeByte(NOT_PRESENT);
    }
    if (latitude != 0.0f) {
      dest.writeByte(PRESENT);
      dest.writeFloat(latitude);
      dest.writeFloat(longitude);
    } else {
      dest.writeByte(NOT_PRESENT);
    }
  }

  /**
   * Keys for the properties
   */
  public interface Keys {
    String ID = "id";
    String NAME = "name";
    String DIRECTION = "direction";
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";
  }

  public final String id;

  public final String name;

  public final String direction;

  public final float latitude;

  public final float longitude;


  private BusStop(final String id, final String name, final String direction,
                  final float latitude, final float longitude) {
    this.id = id;
    this.name = name;
    this.direction = direction;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  private BusStop(Parcel in) {
    id = in.readString();
    name = in.readString();
    boolean present = in.readByte() == PRESENT;
    if (present){
      direction = in.readString();
    } else {
      direction = null;
    }
    present = in.readByte() == PRESENT;
    if (present){
      latitude = in.readFloat();
      longitude = in.readFloat();
    } else {
      latitude = 0.0f;
      longitude = 0.0f;
    }
  }

  /**
   * Copy BusStop data into the given Intent
   *
   * @param intent The Intent for the BusStop data
   */
  public void toIntent(final Intent intent) {
    intent.putExtra(BusStop.Keys.ID, id);
    intent.putExtra(BusStop.Keys.NAME, name);
    intent.putExtra(BusStop.Keys.DIRECTION, direction);
    intent.putExtra(BusStop.Keys.LATITUDE, latitude);
    intent.putExtra(BusStop.Keys.LONGITUDE, longitude);
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("id:" + id)
            .append(" name:" + name)
            .append(" direction:" + direction)
            .append(" @[").append(latitude).append(",").append(longitude).append("]");
    return stringBuilder.toString();
  }

  /**
   * Builder Pattern implementation for the BuStop class
   */
  public static class Builder {

    private String mId;

    private String mName;

    private String mDirection;

    private float mLatitude;

    private float mLongitude;

    /**
     * Creates a Builder for the BusStop class
     *
     * @param id   The id for the BusStop
     * @param name The stopId of the BusStop
     */
    private Builder(final String id, final String name) {
      this.mId = id;
      this.mName = name;
    }

    /**
     * Static Factory method for the Builder of the BusStop object
     *
     * @param id   The id for the BusStop
     * @param name The stopId of the BusStop
     * @return The Builder for the BusStop class
     */
    public static Builder create(final String id, final String name) {
      return new Builder(id, name);
    }

    /**
     * Set the optional direction for the BusStop
     *
     * @param direction The direction for the BusStop
     * @return The BusStop itself
     */
    public Builder withDirection(final String direction) {
      this.mDirection = direction;
      return this;
    }

    /**
     * Set the optional location for the BusStop
     *
     * @param latitude  The route for the BusStop
     * @param longitude The longitude for the BusStop
     * @return The BusStop itself
     */
    public Builder withLocation(final float latitude, final float longitude) {
      this.mLatitude = latitude;
      this.mLongitude = longitude;
      return this;
    }

    /**
     * @return The BusStop with the given data
     */
    public BusStop build() {
      return new BusStop(mId, mName, mDirection, mLatitude, mLongitude);
    }

  }

  /**
   * Utility method that creates a BusStop from data into an Intent
   *
   * @param inputIntent The Intent with the data
   * @return The created BusStop
   */
  public static BusStop fromIntent(final Intent inputIntent) {
    final String id = inputIntent.getStringExtra(BusStop.Keys.ID);
    final String name = inputIntent.getStringExtra(BusStop.Keys.NAME);
    final String direction = inputIntent.getStringExtra(BusStop.Keys.DIRECTION);
    final float latitude = inputIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
    final float longitude = inputIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
    final BusStop busStop = BusStop.Builder.create(id, name)
            .withDirection(direction)
            .withLocation(latitude, longitude)
            .build();
    return busStop;
  }

}
