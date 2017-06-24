package com.github.p4535992.util.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

public class FragmentUtility {

    /*
   * Private constructor
   */
    private FragmentUtility() {
        throw new AssertionError("Never instantiate me!! I'm an utility class!!!");
    }

    @SuppressWarnings("unchecked")
    public static <T extends android.support.v4.app.Fragment> T findFragmentById(AppCompatActivity activity, int layoutFragment) {
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return (T) fragmentManager.findFragmentById(layoutFragment);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentById(FragmentActivity activity, int layoutFragment) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        return (T) fragmentManager.findFragmentById(layoutFragment);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentById(Activity activity, int layoutFragment) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        return (T) fragmentManager.findFragmentById(layoutFragment);
    }

    @SuppressWarnings("unchecked")
    public static <T extends android.support.v4.app.Fragment> T findFragmentByTag(AppCompatActivity activity, String layoutFragmentTag) {
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return (T) fragmentManager.findFragmentByTag(layoutFragmentTag);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentByTag(FragmentActivity activity, String layoutFragmentTag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        return (T) fragmentManager.findFragmentByTag(layoutFragmentTag);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentByTag(Activity activity, String layoutFragmentTag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        return (T) fragmentManager.findFragmentByTag(layoutFragmentTag);
    }

    @SuppressWarnings("unchecked")
    public static <T extends android.support.v4.app.Fragment> T findFragmentById(android.support.v4.app.FragmentManager fragmentManager, int layoutFragment) {
        return (T) fragmentManager.findFragmentById(layoutFragment);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentById(FragmentManager fragmentManager, int layoutFragment) {
        return (T) fragmentManager.findFragmentById(layoutFragment);
    }

    @SuppressWarnings("unchecked")
    public static <T extends android.support.v4.app.Fragment> T findFragmentByTag(android.support.v4.app.FragmentManager fragmentManager, String layoutFragmentTag) {
        return (T) fragmentManager.findFragmentByTag(layoutFragmentTag);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentByTag(FragmentManager fragmentManager, String layoutFragmentTag) {
        return (T) fragmentManager.findFragmentByTag(layoutFragmentTag);
    }

}
