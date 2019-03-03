/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.moodtrack.dal;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * Returns a date object representing some date, however many daysAgo from now.
     *
     * Date today = DateHelper.getTodayPlusDays(0);
     * Date yesterday = DateHelper.getTodayPlusDays(-1);
     * Date twoDaysAgo = DateHelper.getTodayPlusDays(-2);
     * Date lastWeek = DateHelper.getTodayPlusDays(-7);
     * Date twoWeeksAgo = DateHelper.getTodayPlusDays(-14);
     */
    public static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }
}
