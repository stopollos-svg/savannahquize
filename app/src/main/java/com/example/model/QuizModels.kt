package com.example.model

enum class AnimalIconType {
    HIPPO,
    MOSQUITO,
    CAPE_BUFFALO,
    BLACK_MAMBA,
    LEOPARD,
    PUFF_ADDER,
    CROCODILE,
    HYENA,
    BLACK_RHINO,
    HONEY_BADGER,
    TSETSE_FLY,
    ELEPHANT,
    SCORPION,
    OSTRICH,
    WILD_DOG,
    BOOMSLANG,
    BABOON,
    CHEETAH,
    GIRAFFE
}

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val animalName: String,
    val scientificName: String,
    val category: String,
    val dangerLevel: String, // e.g. "Extreme Lethality", "Apex Megafauna", etc.
    val fascinatingFact: String,
    val expertCommentaryCorrect: String,
    val expertCommentaryIncorrect: String,
    val iconType: AnimalIconType
)

data class UserAnswerRecord(
    val questionId: Int,
    val selectedIndex: Int,
    val isCorrect: Boolean,
    val timeSpentSeconds: Int
)

enum class QuizPhase {
    INTRO,
    PLAYING,
    QUESTION_FEEDBACK,
    COMPLETED
}

data class QuizRank(
    val title: String,
    val badgeEmoji: String,
    val subtitle: String,
    val description: String,
    val minPercentage: Int,
    val maxPercentage: Int
) {
    companion object {
        fun fromPercentage(percentage: Int): QuizRank {
            return when {
                percentage <= 30 -> QuizRank(
                    title = "Savannah Tourist",
                    badgeEmoji = "🏕️",
                    subtitle = "Stick to the Safari Jeep!",
                    description = "You survived the tour, but you might want to review the field guide before stepping into the bush. The savannah has no mercy for casual wanderers!",
                    minPercentage = 0,
                    maxPercentage = 30
                )
                percentage <= 70 -> QuizRank(
                    title = "Ranger Trainee",
                    badgeEmoji = "🧭",
                    subtitle = "Promising Bush Instincts!",
                    description = "Impressive bushcraft! You know which watering holes to avoid and how to spot a charging buffalo. A few more expeditions and you'll lead your own patrol.",
                    minPercentage = 31,
                    maxPercentage = 70
                )
                else -> QuizRank(
                    title = "Apex Predator",
                    badgeEmoji = "🦁",
                    subtitle = "Master of the Savannah!",
                    description = "Magnificent! You possess legendary wildlife wisdom. You outsmarted the deadliest snakes, crocodiles, and megafauna. The entire savannah bows to your knowledge!",
                    minPercentage = 71,
                    maxPercentage = 100
                )
            }
        }
    }
}

data class QuizSettings(
    val timerSeconds: Int = 25, // 0 = Untimed, 15 = Blitz, 25 = Standard, 40 = Relaxed
    val soundEnabled: Boolean = true,
    val hapticsEnabled: Boolean = true
)
