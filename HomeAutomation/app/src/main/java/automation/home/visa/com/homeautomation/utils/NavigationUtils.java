package automation.home.visa.com.homeautomation.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class NavigationUtils {
    //Nagivate to Activity
    public static void navigateToActivity(final Context mContext, final Class<?> targetActivity){
        Intent intent = new Intent(mContext, targetActivity);
        mContext.startActivity(intent);
    }

    public static void navigateToActivity(final Context mContext, final Class<?> targetActivity, String key, String data){
        Intent intent = new Intent(mContext, targetActivity);
        intent.putExtra(key,data);
        mContext.startActivity(intent);
    }

    public static void navigateToActivity(final Context mContext, final Class<?> targetActivity, String key, boolean data){
        Intent intent = new Intent(mContext, targetActivity);
        intent.putExtra(key,data);
        mContext.startActivity(intent);
    }


    public static void navigateToFragment(final Context mContext, final Fragment fragment,
                                          int containerId, String TAG, boolean isAddedToBackStack){
        if(mContext!=null){
            if(!isAddedToBackStack) {
                ((AppCompatActivity) mContext).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, fragment, TAG)
                        .commitAllowingStateLoss();
            }
            else{
                ((AppCompatActivity) mContext).getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(TAG)
                        .replace(containerId, fragment, TAG)
                        .commitAllowingStateLoss();
            }
        }
    }

    public static void removeFragment(final Context mContext, String TAG){
        Fragment fragment = ((AppCompatActivity) mContext).getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment!=null){
            ((AppCompatActivity) mContext).getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commitAllowingStateLoss();
        }
    }


}
