package org.parowings

import java.lang.ref.WeakReference
import android.app.Activity

object ActivityHolder {
    private var currentActivity: WeakReference<Activity>? = null

    fun setActivity(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    fun clearActivity() {
        currentActivity?.clear()
        currentActivity = null
    }

    fun getActivity(): Activity? {
        return currentActivity?.get()
    }
}