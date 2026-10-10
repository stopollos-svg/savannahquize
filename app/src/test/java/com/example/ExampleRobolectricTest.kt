package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.QuizRepository
import com.example.model.QuizRank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Savannah Quiz", appName)
    }

    @Test
    fun `verify exactly twenty questions in repository`() {
        assertEquals(20, QuizRepository.questions.size)
    }

    @Test
    fun `verify all questions have valid options and correct answer index`() {
        QuizRepository.questions.forEachIndexed { index, q ->
            assertEquals(index + 1, q.id)
            assertEquals("Question ${q.id} must have 4 options", 4, q.options.size)
            assertTrue("Correct answer index must be in range 0..3", q.correctAnswerIndex in 0..3)
            assertTrue("Question text must not be empty", q.question.isNotBlank())
            assertTrue("Animal name must not be empty", q.animalName.isNotBlank())
            assertTrue("Fascinating fact must not be empty", q.fascinatingFact.isNotBlank())
            assertTrue("Expert commentary on correct must not be empty", q.expertCommentaryCorrect.isNotBlank())
            assertTrue("Expert commentary on incorrect must not be empty", q.expertCommentaryIncorrect.isNotBlank())
        }
    }

    @Test
    fun `verify rank thresholds`() {
        val touristRank = QuizRank.fromPercentage(25)
        assertEquals("Savannah Tourist", touristRank.title)

        val traineeRank = QuizRank.fromPercentage(50)
        assertEquals("Ranger Trainee", traineeRank.title)

        val apexRank = QuizRank.fromPercentage(85)
        assertEquals("Apex Predator", apexRank.title)
    }
}
