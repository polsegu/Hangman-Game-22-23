package com.example.hanggame.util
import android.content.Context
import android.preference.PreferenceManager
import com.example.hanggame.managers.GameManager

class PrefUtils {

    companion object {
        fun getTimerLength(context: Context): Int{
            //Placeholder
            return 1
        }
        private const val PREVIOUS_TIMER_LENGTH = "com.example.hanggame.timer.previous_timer"
        private const val TIMER_STATE_ID = "com.example.hanggame.timer.state_timer"

        fun getPreviusTimerSecs(context: Context): Long{
            //Placeholder
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH, 0)
        }

        fun setPreviousTimerSecs(seconds: Long, context: Context)
        {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH, seconds)
            editor.apply()
        }

        fun getPreviousState(context: Context): GameManager.TimerState{
            //Placeholder
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return GameManager.TimerState.values()[ordinal]
        }

        fun setPreviousState(state: GameManager.TimerState, context: Context)
        {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

    }
}