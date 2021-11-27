package com.zinoview.tzfilmsapp.presentation

interface StringLengthMapper {

    fun map(srcString: String) : String

    class Base : StringLengthMapper {

        override fun map(srcString: String): String {
            return if (srcString.length > LENGTH_MAX) {
                srcString.substring(MIN_INDEX, MAX_INDEX) + POINTERS
            } else {
                srcString
            }
        }

        private companion object {

            private const val MIN_INDEX = 0
            private const val MAX_INDEX = 11
            private const val LENGTH_MAX = 13
            private const val POINTERS = "..."
        }
    }
}