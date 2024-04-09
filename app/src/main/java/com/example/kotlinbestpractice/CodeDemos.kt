package com.example.kotlinbestpractice

import com.example.kotlinbestpractice.movies.domain.model.Movie

fun main() {

    calculateCircleArea(2.2)
    // while
    var counter = 0
    while( counter < 100) {
        println(counter)
        counter++
    }

    // do while
    var counter2 = 0
    do {
        println(counter2)
        counter2++
    } while ( counter2 != 100)

}



fun calculateCircleArea(radius: Double): Double {
    return 3.14159265359 * radius * radius
}

fun calculateCylinderVolume(radius: Double, height: Double): Double {
    return 3.14159265359 * radius * radius * height
}

fun calculateSphereVolume(radius: Double): Double {
    return (4 / 3) * 3.14159265359 * radius * radius * radius
}

fun divide(a: Int, b: Int): Int {

    val user = true

    if ((isAdult(user) && hasValidPayment(user)) || (isStudent(user) && hasValidStudentID(user))) {
        // Process the order
    } else {
        // Handle eligibility issues
    }
    try {
        return a / b
    } catch (e: Exception) {
        // Ignore the exception and continue
        return 0
    }

}

fun isAdult(user: Boolean): Boolean {
    return  user
}
fun hasValidPayment(user: Boolean): Boolean {
    return  !user
}
fun isStudent(user: Boolean): Boolean {
    return  user
}
fun hasValidStudentID(user: Boolean): Boolean {
    return  !user
}

fun concatenateStrings(strings: List<String>): String {
    var result = ""
    for (str in strings) {
        result += str
    }
    return result
}

fun zz(data: List<Movie>) {

    // Data Manipulation
    val processedData = processData(data)

    // Complex Calculations
    val intermediateResult = complexCalculation(processedData)

    // More Data Manipulation
    val finalData = furtherProcessData(intermediateResult)

    // Additional Complex Calculations
    val finalResult = moreComplexCalculations(finalData)

    // Report Generation
    val report = generateReportFromResult(finalResult)

    // Email the Report
    val emailBody = createEmailBody(report)
    val recipients = getEmailRecipients()
    sendEmail(emailBody, recipients)
}

fun processData(data: List<Movie>): List<Movie> {
    return data
}
fun complexCalculation(data: List<Movie>): List<Movie> {
    return data
}
fun furtherProcessData(data: List<Movie>): List<Movie> {
    return data
}
fun moreComplexCalculations(data: List<Movie>): List<Movie> {
    return data
}
fun generateReportFromResult(data: List<Movie>): List<Movie> {
    return data
}
fun createEmailBody(data: List<Movie>): List<Movie> {
    return data
}
fun getEmailRecipients(): List<Movie> {
    return listOf()
}
fun sendEmail(data1: List<Movie>, data: List<Movie>): List<Movie> {
    return data
}

fun calculateSalePrice(itemPrice: Double, quantityInStock: Double, margin: Double=0.0): Double {
    return if (quantityInStock < 10) {
        if (itemPrice <= 10.0) {
            val newMargin = margin * 2 // 2x the margin for cheap items
            calculateSalePrice(itemPrice, newMargin)
        } else if (itemPrice > 500.0) {
            val newMargin = margin * 1.5 // 1.5x the margin for expensive
            calculateSalePrice(itemPrice, newMargin)
        } else {
            val newMargin = margin * 1.7 // 1.7x the margin in between items
            calculateSalePrice(itemPrice, newMargin)
        }
    } else if (quantityInStock < 500) {
        val newMargin = margin * 1.3 // 1.3x the margin
        calculateSalePrice(itemPrice, newMargin)
    } else {
        calculateSalePrice(itemPrice, margin)
    }
}
