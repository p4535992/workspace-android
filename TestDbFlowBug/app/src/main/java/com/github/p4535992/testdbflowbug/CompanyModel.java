package com.github.p4535992.testdbflowbug;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class, name = "company_models")
public class CompanyModel extends BaseModel implements Parcelable{

    @PrimaryKey(autoincrement = true)
    public int id;
    @Column
    public String name;
    @Column
    public String address;
    @ForeignKey
    public int locationId;

    /**
     * Constant that identify an existing data
     */
    private static final byte PRESENT = 1;

    /**
     * Constant that identify a NON existing data
     */
    private static final byte NOT_PRESENT = 0;


    /**
     * Always use a default constructor
     */
    public CompanyModel(){}

    private CompanyModel(final int id,final String name, final String address, final int locationId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.locationId = locationId;
    }

    private CompanyModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.locationId = builder.locationId;
    }

    /////////////////////////////////
    // UTILITY
    ////////////////////////////////
    public interface Keys{
        String ID = "id";
        String NAME = "name";
        String ADDRESS = "address";
        String LOCATIONID = "locationId";
    }

    //<editor-fold desc="CURSOR">
    /**
     * Utility method that returns a BusStop from a given cursor
     *
     * @param cursor The Cursor for the data
     * @return The BusStop if any
     */
    public static CompanyModel fromCursor(@NonNull final Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(Keys.ID));
        String name = cursor.getString(cursor.getColumnIndex(Keys.NAME));
        String address = cursor.getString(cursor.getColumnIndex(Keys.ADDRESS));
        int locationid = cursor.getInt(cursor.getColumnIndex(Keys.LOCATIONID));
        return CompanyModel.Builder.create().withId(id).withName(name)
                .withAddress(address).withLocationId(locationid)
                .build();
    }
    //</editor-fold>

    //<editor-fold desc="INTENT">
    /**
     * Copy BusStop data into the given Intent
     *
     * @param intent The Intent for the BusStop data
     */
    public void toIntent(final Intent intent) {
        intent.putExtra(Keys.ID, id);
        intent.putExtra(Keys.NAME, name);
        intent.putExtra(Keys.ADDRESS, address);
        intent.putExtra(Keys.LOCATIONID, locationId);
    }

    /**
     * Utility method that creates a BusStop from data into an Intent
     *
     * @param inputIntent The Intent with the data
     * @return The created BusStop
     */
    public static CompanyModel fromIntent(final Intent inputIntent) {
        final int id = inputIntent.getIntExtra(Keys.ID,0);
        final String name = inputIntent.getStringExtra(Keys.NAME);
        final String address = inputIntent.getStringExtra(Keys.ADDRESS);
        final int locationId = inputIntent.getIntExtra(Keys.LOCATIONID,0);
        final CompanyModel companyModel = CompanyModel.Builder.create()
                .withId(id).withName(name).withAddress(address)
                .withLocationId(locationId)
                .build();
        return companyModel;
    }
    //</editor-fold>

    //<editor-fold desc="PARCELABLE">
    protected CompanyModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        locationId = in.readInt();
    }

    public static final Creator<CompanyModel> CREATOR = new Creator<CompanyModel>() {
        @Override
        public CompanyModel createFromParcel(Parcel in) {
            return new CompanyModel(in);
        }

        @Override
        public CompanyModel[] newArray(int size) {
            return new CompanyModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        if (locationId > 0) {
            dest.writeByte(PRESENT);
            dest.writeInt(locationId);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
    }
    //</editor-fold>

    //<editor-fold desc="BUILDER">
    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private int id;
        private String name;
        private String address;
        private int locationId;

        private Builder(){}

        private Builder(final int id, final String name, final String address, final int locationId) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.locationId = locationId;
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final int id, final String name, final String address, final int locationId) {
            return new Builder(id,name,address,locationId);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withLocationId(int locationId) {
            this.locationId = locationId;
            return this;
        }

        public CompanyModel build() {
            return new CompanyModel(this);
        }
    }
    //</editor-fold>
}
