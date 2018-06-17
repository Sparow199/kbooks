package com.zenika.kbooks.util.rest

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.RequestParam

/**
 * BeanParam that stores pagination info.
 */
data class PaginationDto(
        @RequestParam("page")
        var page: Int = 0,
        @RequestParam("size")
        var size: Int = 10,
        @RequestParam("sort")
        var sort: String? = null
) {
    fun toPageable(maxSize: Int = 10) : Pageable {
        // Make sure page is greater than zero.
        val checkedPage = Math.max(0, page)
        // Make sure size is between one and maxSize.
        val checkedSize = if (size in 1..maxSize) size else maxSize
        // Make sure sort is not null.
        val checkedSort = sort ?: ""
        // Convert to Sort object.
        val pageSort = when {
            checkedSort.startsWith("+") -> Sort.by(Sort.Direction.ASC, checkedSort.substring(1..checkedSort.length))
            checkedSort.startsWith("-") -> Sort.by(Sort.Direction.DESC, checkedSort.substring(1..checkedSort.length))
            !checkedSort.isEmpty() -> Sort.by(Sort.Direction.ASC, sort)
            else -> Sort.unsorted()
        }

        return PageRequest.of(checkedPage, checkedSize, pageSort)
    }
}