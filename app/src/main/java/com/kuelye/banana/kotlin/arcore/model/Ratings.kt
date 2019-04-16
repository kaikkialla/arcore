package com.kuelye.banana.kotlin.arcore.model

class Ratings {

    var title: String? = null;
    var ratings: MutableList<Rating>? = null;

    companion object {

        const val TEST_RATINGS_COUNT = 5;

        fun createTestRatings(): Ratings {
            val ratings = Ratings()
            ratings.title = "DIGITAL BANANA"
            ratings.ratings = ArrayList()
            for (i in 0..TEST_RATINGS_COUNT) {
                ratings.ratings?.add(Rating("Пользователь $i", i))
            }
            return ratings
        }

    }

}