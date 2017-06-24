package com.example.tenti.apobusmy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is the class that contains the information related to the ArrivalTime
 */
public final class ArrivalTime implements Parcelable {

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
  public static final Parcelable.Creator<ArrivalTime> CREATOR = new Parcelable.Creator<ArrivalTime>() {
    public ArrivalTime createFromParcel(Parcel in) {
      return new ArrivalTime(in);
    }

    public ArrivalTime[] newArray(int size) {
      return new ArrivalTime[size];
    }
  };

  public final String id;

  public final String stopId;

  public final String direction;

  public final String route;

  public final long estimatedTime;


  private ArrivalTime(final String id, final String name, final String direction,
                      final String route, final long estimatedTime) {
    this.id = id;
    this.stopId = name;
    this.direction = direction;
    this.route = route;
    this.estimatedTime = estimatedTime;
  }

  private ArrivalTime(Parcel in) {
    id = in.readString();
    stopId = in.readString();
    estimatedTime = in.readLong();
    boolean present = in.readByte() == PRESENT;
    if (present) {
      direction = in.readString();
    } else {
      direction = null;
    }
    present = in.readByte() == PRESENT;
    if (present) {
      route = in.readString();
    } else {
      route = null;
    }
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("id:" + id)
            .append(" stopId:" + stopId)
            .append(" direction:" + direction)
            .append(" route:" + route)
            .append(" @ ").append(estimatedTime);
    return stringBuilder.toString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(stopId);
    dest.writeLong(estimatedTime);
    if (direction != null) {
      dest.writeByte(PRESENT);
      dest.writeString(direction);
    } else {
      dest.writeByte(NOT_PRESENT);
    }
    if (route != null) {
      dest.writeByte(PRESENT);
      dest.writeString(route);
    } else {
      dest.writeByte(NOT_PRESENT);
    }
  }

  /**
   * Builder Pattern implementation for the ArrivalTime class
   */
  public static class Builder {

    private String mId;

    private String mStopId;

    private String mDirection;

    private String mRoute;

    private long mArrivalTime;

    /**
     * Creates a Builder for the ArrivalTime class
     *
     * @param id          The id for the ArrivalTime
     * @param stopId      The stopId of the BusStop
     * @param arrivalTime The time of the arrival
     */
    private Builder(final String id, final String stopId, final long arrivalTime) {
      this.mId = id;
      this.mStopId = stopId;
      this.mArrivalTime = arrivalTime;
    }

    /**
     * Static Factory method for the Builder of the ArrivalTime object
     *
     * @param id          The id for the ArrivalTime
     * @param stopId      The stopId of the BusStop
     * @param arrivalTime The time of the arrival
     * @return The Builder for the ArrivalTime class
     */
    public static Builder create(final String id, final String stopId, final long arrivalTime) {
      return new Builder(id, stopId, arrivalTime);
    }

    /**
     * Set the optional direction for the ArrivalTime
     *
     * @param direction The direction for the ArrivalTime
     * @return The ArrivalTime itself
     */
    public Builder withDirection(final String direction) {
      this.mDirection = direction;
      return this;
    }

    /**
     * Set the optional route for the ArrivalTime
     *
     * @param route The direction for the ArrivalTime
     * @return The ArrivalTime itself
     */
    public Builder withRoute(final String route) {
      this.mRoute = route;
      return this;
    }

    /**
     * @return The ArrivalTime with the given data
     */
    public ArrivalTime build() {
      return new ArrivalTime(mId, mStopId, mDirection, mRoute, mArrivalTime);
    }

  }

}
