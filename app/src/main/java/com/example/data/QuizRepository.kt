package com.example.data

import com.example.model.AnimalIconType
import com.example.model.QuizQuestion

object QuizRepository {

    val questions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            question = "Which Savannah animal is considered the most deadly to humans, killing more people annually than lions or elephants?",
            options = listOf(
                "Hippopotamus",
                "African Lion",
                "Nile Crocodile",
                "African Elephant"
            ),
            correctAnswerIndex = 0,
            animalName = "Hippopotamus",
            scientificName = "Hippopotamus amphibius",
            category = "River Giants",
            dangerLevel = "Territorial Behemoth",
            fascinatingFact = "Hippos are fiercely territorial and can sprint up to 30 km/h (19 mph) on land. Their canine tusks can reach 50 cm (20 inches) with a crushing 2,000 psi bite!",
            expertCommentaryCorrect = "Spot on, explorer! Never underestimate a hippo—they can capsize and bite a wooden canoe cleanly in two!",
            expertCommentaryIncorrect = "A common safari misconception! The lumbering Hippopotamus claims roughly 500 human lives each year along Africa's waterways.",
            iconType = AnimalIconType.HIPPO
        ),
        QuizQuestion(
            id = 2,
            question = "What tiny creature is actually the deadliest animal in Africa, responsible for over 500,000 deaths annually?",
            options = listOf(
                "Tsetse Fly",
                "Mosquito",
                "Driver Ant",
                "African Assassin Bug"
            ),
            correctAnswerIndex = 1,
            animalName = "Mosquito (Anopheles)",
            scientificName = "Anopheles gambiae",
            category = "Microscopic Threats",
            dangerLevel = "Continental Killer",
            fascinatingFact = "Female Anopheles mosquitoes transmit Plasmodium falciparum malaria parasites, resulting in more human fatalities than every apex savannah predator combined.",
            expertCommentaryCorrect = "Brilliant deduction! The deadliest beast in the savannah isn't the biggest, but this tiny airborne vector.",
            expertCommentaryIncorrect = "Look closer to the ground and air! The humble Mosquito is responsible for over half a million deaths across Africa every single year.",
            iconType = AnimalIconType.MOSQUITO
        ),
        QuizQuestion(
            id = 3,
            question = "Which large herbivore is known as one of the 'Big Five' and is famous for being incredibly unpredictable and aggressive, sometimes stalking humans if injured?",
            options = listOf(
                "Cape Buffalo",
                "White Rhinoceros",
                "Common Warthog",
                "Blue Wildebeest"
            ),
            correctAnswerIndex = 0,
            animalName = "Cape Buffalo",
            scientificName = "Syncerus caffer",
            category = "The Big Five",
            dangerLevel = "The 'Black Death'",
            fascinatingFact = "Known to hunters as 'The Widowmaker' or 'Black Death', Cape Buffaloes have fused bone helmets ('bosses') that can deflect high-caliber rounds and are famous for ambushing pursuers.",
            expertCommentaryCorrect = "Sharp instincts! Solitary old bulls ('Dagga Boys') are legendary among game rangers for their relentless cunning.",
            expertCommentaryIncorrect = "Watch your flank! It's the formidable Cape Buffalo. Unlike domestic cattle, they have never been tamed and will circle back to ambush threats.",
            iconType = AnimalIconType.CAPE_BUFFALO
        ),
        QuizQuestion(
            id = 4,
            question = "Which snake is considered the deadliest in Africa due to its immense speed, aggression, and highly toxic venom?",
            options = listOf(
                "Puff Adder",
                "Boomslang",
                "Black Mamba",
                "Egyptian Cobra"
            ),
            correctAnswerIndex = 2,
            animalName = "Black Mamba",
            scientificName = "Dendroaspis polylepis",
            category = "Venomous Serpents",
            dangerLevel = "Rapid Neurotoxic Lethality",
            fascinatingFact = "Reaching speeds over 19 km/h (12 mph), a single bite from a Black Mamba injects enough potent neurotoxin and cardiotoxin to kill multiple adult humans within hours.",
            expertCommentaryCorrect = "Crikey, right on target! Named not for its brownish scales, but for the ink-black lining of its threatening, open mouth!",
            expertCommentaryIncorrect = "Be cautious! That lethal title belongs to the feared Black Mamba—lightning quick, agile, and packing deadly neurotoxins.",
            iconType = AnimalIconType.BLACK_MAMBA
        ),
        QuizQuestion(
            id = 5,
            question = "Which stealthy predator is considered by some wildlife experts to be the most dangerous big cat to encounter on foot?",
            options = listOf(
                "Cheetah",
                "Leopard",
                "Caracal",
                "Serval"
            ),
            correctAnswerIndex = 1,
            animalName = "Leopard",
            scientificName = "Panthera pardus",
            category = "Stealth Predators",
            dangerLevel = "Silent Arboreal Ambush",
            fascinatingFact = "Leopards are supreme ambush masters possessing incredible pound-for-pound strength, capable of hoisting heavy antelope carcasses up into thorny acacia branches to evade hyenas.",
            expertCommentaryCorrect = "Masterful observation! A leopard will freeze motionlessly and let an unsuspecting walker pass within feet before striking.",
            expertCommentaryIncorrect = "Look up in the trees! It's the elusive Leopard. Unmatched stealth and climbing power make it the cat rangers fear most on foot patrols.",
            iconType = AnimalIconType.LEOPARD
        ),
        QuizQuestion(
            id = 6,
            question = "Which snake is responsible for the most human fatalities in Africa, often because it relies on camouflage and lies still until stepped on?",
            options = listOf(
                "Black Mamba",
                "Gaboon Viper",
                "Puff Adder",
                "Green Mamba"
            ),
            correctAnswerIndex = 2,
            animalName = "Puff Adder",
            scientificName = "Bitis arietans",
            category = "Venomous Serpents",
            dangerLevel = "Camouflaged Cytotoxic Threat",
            fascinatingFact = "While most snakes flee human footsteps, the thick-bodied Puff Adder freezes in leaf litter using superb camouflage, striking with one of the fastest speeds recorded among vipers when stepped on.",
            expertCommentaryCorrect = "Top-tier savannah knowledge! The puff adder's reluctance to flee is precisely why it causes the most snakebite casualties in Africa.",
            expertCommentaryIncorrect = "Watch your step in the grass! It's the Puff Adder. Because they blend perfectly and don't slither away, thousands step on them accidentally.",
            iconType = AnimalIconType.PUFF_ADDER
        ),
        QuizQuestion(
            id = 7,
            question = "The Nile Crocodile is a fearsome predator. What unique physiological feature allows it to hold its breath for over an hour?",
            options = listOf(
                "Secondary air-sac lungs",
                "A specialized four-chambered heart",
                "Scale oxygen absorption",
                "Throat gill slits"
            ),
            correctAnswerIndex = 1,
            animalName = "Nile Crocodile",
            scientificName = "Crocodylus niloticus",
            category = "Aquatic Apex",
            dangerLevel = "Prehistoric Ambush",
            fascinatingFact = "Crocodiles possess a unique 'Foramen of Panizza' valve in their 4-chambered heart that can shunt deoxygenated blood away from the lungs to conserve oxygen during deep submergence.",
            expertCommentaryCorrect = "Superb anatomical wisdom! This remarkable cardiovascular adaptation allows them to lie submerged like a sunken log waiting for thirsty game.",
            expertCommentaryIncorrect = "Fascinating anatomy at work! It is their specialized four-chambered heart with a cog-wheel valve that recirculates blood and conserves oxygen underwater.",
            iconType = AnimalIconType.CROCODILE
        ),
        QuizQuestion(
            id = 8,
            question = "Despite being smaller and often considered scavengers, which animal is actually a highly efficient, intelligent pack hunter with a bite force stronger than a lion?",
            options = listOf(
                "Black-backed Jackal",
                "Honey Badger",
                "Spotted Hyena",
                "Aardwolf"
            ),
            correctAnswerIndex = 2,
            animalName = "Spotted Hyena",
            scientificName = "Crocuta crocuta",
            category = "Pack Hunters",
            dangerLevel = "Bone-Crushing Clan",
            fascinatingFact = "Spotted hyenas hunt up to 95% of their own food in complex coordinated clans. Their massive jaws exert over 1,100 psi, effortlessly splintering large zebra and giraffe bones.",
            expertCommentaryCorrect = "Spot on! Lion-King mythology got it wrong—hyenas are brilliant cooperative hunters, and lions frequently steal kills from *them*!",
            expertCommentaryIncorrect = "Don't underestimate the clan! The Spotted Hyena boasts a bite force greater than an adult male lion and hunts the vast majority of its own prey.",
            iconType = AnimalIconType.HYENA
        ),
        QuizQuestion(
            id = 9,
            question = "Which of these large animals has notoriously poor eyesight, making them easily startled and prone to aggressive defensive charges?",
            options = listOf(
                "Black Rhinoceros",
                "Plains Zebra",
                "Blue Wildebeest",
                "African Lion"
            ),
            correctAnswerIndex = 0,
            animalName = "Black Rhinoceros",
            scientificName = "Diceros bicornis",
            category = "Armored Megafauna",
            dangerLevel = "Blind Kinetic Charge",
            fascinatingFact = "Black rhinos cannot clearly see a stationary human beyond 30 meters (98 ft). When startled by sound or scent, they charge first at speeds up to 55 km/h (34 mph) and ask questions never!",
            expertCommentaryCorrect = "Dead right! With hooked lips, sharp horns, and terrible vision, black rhinos will charge termite mounds, bushes, and safari vehicles alike!",
            expertCommentaryIncorrect = "Careful in the brush! The Black Rhino has notoriously blurry vision and compensates with hyper-aggressive preemptive charges at any strange sound.",
            iconType = AnimalIconType.BLACK_RHINO
        ),
        QuizQuestion(
            id = 10,
            question = "Which small, fierce animal is known to aggressively defend itself and its food against much larger predators, and is even largely immune to snake venom?",
            options = listOf(
                "Meerkat",
                "Honey Badger",
                "Banded Mongoose",
                "African Civet"
            ),
            correctAnswerIndex = 1,
            animalName = "Honey Badger (Ratel)",
            scientificName = "Mellivora capensis",
            category = "Fearless Nomads",
            dangerLevel = "Indomitable Brawler",
            fascinatingFact = "Honey badgers possess rubbery, quarter-inch-thick skin that can withstand porcupine quills and machete cuts. Even after being bitten by a puff adder, they sleep off the venom and wake up eating the snake!",
            expertCommentaryCorrect = "Guinness World Record holder for most fearless creature! Honey badgers have chased full prides of lions off fresh kills!",
            expertCommentaryIncorrect = "Never back down! The Honey Badger's rubbery skin and mutated nicotinic acetylcholine receptors make it practically impervious to snake venom.",
            iconType = AnimalIconType.HONEY_BADGER
        ),
        QuizQuestion(
            id = 11,
            question = "Which insect, known for carrying sleeping sickness, makes certain areas of the savannah virtually uninhabitable for humans and livestock?",
            options = listOf(
                "Anopheles Mosquito",
                "Tsetse Fly",
                "African Army Ant",
                "Sausage Tree Wasp"
            ),
            correctAnswerIndex = 1,
            animalName = "Tsetse Fly",
            scientificName = "Glossina morsitans",
            category = "Microscopic Threats",
            dangerLevel = "Sleeping Sickness Vector",
            fascinatingFact = "Tsetse flies bite with serrated mouthparts to feed on vertebrate blood, transmitting protozoan parasites (Trypanosoma) that attack the central nervous system.",
            expertCommentaryCorrect = "Superb historical ecology knowledge! Tsetse fly zones prevented colonial cattle expansion and preserved vast wilderness areas as national parks.",
            expertCommentaryIncorrect = "Listen for that buzzing! It is the Tsetse Fly, vector of human sleeping sickness and nagana in livestock across sub-Saharan Africa.",
            iconType = AnimalIconType.TSETSE_FLY
        ),
        QuizQuestion(
            id = 12,
            question = "What is the primary reason an African Elephant might suddenly become highly aggressive and charge a vehicle or person?",
            options = listOf(
                "Competition for watering holes",
                "Protecting their young/calves",
                "Carnivorous hunting instinct",
                "Attracted by vehicle engine sounds"
            ),
            correctAnswerIndex = 1,
            animalName = "African Bush Elephant",
            scientificName = "Loxodonta africana",
            category = "The Big Five",
            dangerLevel = "6-Ton Powerhouse",
            fascinatingFact = "Protective matriarchs and mothers will relentlessly eliminate any perceived danger near a newborn calf. Bull elephants undergoing 'musth' also experience a 60-fold testosterone spike!",
            expertCommentaryCorrect = "Exactly right! A mother elephant's protective bond is unbreakable. If she tucks her ears back and lowers her head silently, retreat immediately!",
            expertCommentaryIncorrect = "Family always comes first! Elephants are gentle herbivores, but a mother protecting her calf will overturn trucks without hesitation.",
            iconType = AnimalIconType.ELEPHANT
        ),
        QuizQuestion(
            id = 13,
            question = "Which venomous arachnid found in the savannah has large, powerful pincers but a relatively mild sting compared to others of its kind?",
            options = listOf(
                "Deathstalker Scorpion",
                "Emperor Scorpion",
                "Parabuthus Granulatus",
                "Spitting Thicktail Scorpion"
            ),
            correctAnswerIndex = 1,
            animalName = "Emperor Scorpion",
            scientificName = "Pandinus imperator",
            category = "Arachnid Predators",
            dangerLevel = "Heavy Claws, Mild Venom",
            fascinatingFact = "A classic rule in arachnology: scorpions with massive muscular pincers (chelae) rely on physical crushing strength, whereas slender-clawed scorpions carry lethal neurotoxins.",
            expertCommentaryCorrect = "Top-notch survival tip! Big claws mean little venom punch; skinny claws and a chunky tail mean run for your life!",
            expertCommentaryIncorrect = "Remember the claw rule! The Emperor Scorpion uses its huge, powerful claws to crush insects, wielding venom no worse than a bee sting.",
            iconType = AnimalIconType.SCORPION
        ),
        QuizQuestion(
            id = 14,
            question = "Though rarely attacking humans, which massive bird has a kick powerful enough to kill a lion if threatened?",
            options = listOf(
                "Kori Bustard",
                "Secretarybird",
                "Ostrich",
                "Martial Eagle"
            ),
            correctAnswerIndex = 2,
            animalName = "Common Ostrich",
            scientificName = "Struthio camelus",
            category = "Avian Giants",
            dangerLevel = "Lethal Talon Kick",
            fascinatingFact = "An ostrich kicks forward with an estimated force exceeding 2,000 psi, armed with a 4-inch (10 cm) razor-sharp toe claw capable of disemboweling hyenas and lions.",
            expertCommentaryCorrect = "Bullseye! Standing nearly 9 feet tall and running at 70 km/h, an ostrich is a living dinosaur with a devastating front kick!",
            expertCommentaryIncorrect = "Don't mock the big bird! An Ostrich's forward kick packs enough power and claw sharpness to crack a lion's skull or pierce vitals.",
            iconType = AnimalIconType.OSTRICH
        ),
        QuizQuestion(
            id = 15,
            question = "Which wild dog species is one of the most successful predators in Africa, with a hunt success rate of over 80%?",
            options = listOf(
                "African Wild Dog / Painted Dog",
                "Black-backed Jackal",
                "Bat-eared Fox",
                "Ethiopian Wolf"
            ),
            correctAnswerIndex = 0,
            animalName = "African Wild Dog (Painted Dog)",
            scientificName = "Lycaon pictus",
            category = "Coordinated Packs",
            dangerLevel = "Relentless Marathon Hunters",
            fascinatingFact = "African Wild Dogs hunt through extraordinary pack communication and endurance, pursuing antelope at 60 km/h over miles. Their 80%+ kill rate easily surpasses lions (25%)!",
            expertCommentaryCorrect = "Magnificent answer! Their tri-colored coats are unique as fingerprints, and their pack altruism caring for pups and injured adults is unmatched.",
            expertCommentaryIncorrect = "Teamwork makes the hunt! It's the African Wild Dog (Painted Dog). Their marathon endurance gives them an unmatched 80%+ success rate.",
            iconType = AnimalIconType.WILD_DOG
        ),
        QuizQuestion(
            id = 16,
            question = "The boomslang is a highly venomous snake. Unlike many other venomous snakes that have fangs at the front, where are the boomslang's fangs located?",
            options = listOf(
                "The roof of the palate",
                "The back of the jaw / rear-fanged",
                "Lower jaw tip",
                "Retractable beneath the tongue"
            ),
            correctAnswerIndex = 1,
            animalName = "Boomslang",
            scientificName = "Dispholidus typus",
            category = "Venomous Serpents",
            dangerLevel = "Potent Hemotoxic Rear-Fangs",
            fascinatingFact = "Boomslangs are opisthoglyphous (rear-fanged). They can dislocate their jaws up to 170 degrees to bite, delivering hemotoxins that disable blood clotting and cause severe internal bleeding.",
            expertCommentaryCorrect = "Outstanding herpetology knowledge! Their name means 'tree snake' in Afrikaans, and their venom was once thought harmless until famous cases proved its extreme lethality.",
            expertCommentaryIncorrect = "Watch the branches! The Boomslang is rear-fanged. It must chew with the back of its jaw to pump its slow-acting, devastating hemotoxin.",
            iconType = AnimalIconType.BOOMSLANG
        ),
        QuizQuestion(
            id = 17,
            question = "What large primate, generally peaceful, can become incredibly dangerous if it feels its troop is threatened, displaying immense strength?",
            options = listOf(
                "Chacma Baboon",
                "Vervet Monkey",
                "Blue Monkey",
                "Mohol Bushbaby"
            ),
            correctAnswerIndex = 0,
            animalName = "Chacma Baboon",
            scientificName = "Papio ursinus",
            category = "Troop Defenders",
            dangerLevel = "5cm Canine Powerhouse",
            fascinatingFact = "Adult male baboons have dagger-like canines measuring up to 5 cm (2 inches)—longer than a leopard's! Coalitions of males will fearlessly charge leopards to protect their troop.",
            expertCommentaryCorrect = "Spot on! When alarmed, the troop bark reverberates across the cliffs, and a mob of large males can rip apart threatening predators.",
            expertCommentaryIncorrect = "Don't underestimate the troop! The Chacma Baboon has massive canine teeth and immense muscular strength when defending its family.",
            iconType = AnimalIconType.BABOON
        ),
        QuizQuestion(
            id = 18,
            question = "Which big cat relies primarily on sheer speed to catch its prey, making it less of an ambush threat to humans but deadly to small gazelles?",
            options = listOf(
                "Cheetah",
                "Leopard",
                "Serval",
                "Caracal"
            ),
            correctAnswerIndex = 0,
            animalName = "Cheetah",
            scientificName = "Acinonyx jubatus",
            category = "Velocity Sprinters",
            dangerLevel = "High-Speed Specialist",
            fascinatingFact = "Cheetahs can accelerate from 0 to 97 km/h (60 mph) in just 3 seconds, using enlarged nasal passages, deep chests, and non-retractable claws that grip the soil like running spikes.",
            expertCommentaryCorrect = "Swift as the wind! Because of their lightweight build and small jaw muscles, wild cheetahs almost never attack humans and avoid confrontations.",
            expertCommentaryIncorrect = "Think of pure acceleration! The Cheetah is built for open-plains velocity rather than ambush power, posing virtually zero threat to humans.",
            iconType = AnimalIconType.CHEETAH
        ),
        QuizQuestion(
            id = 19,
            question = "What aquatic animal, alongside the hippo, makes crossing African rivers incredibly dangerous due to its ambush tactics?",
            options = listOf(
                "African Tigerfish",
                "Nile Crocodile",
                "Goliath Bullfrog",
                "Electric Catfish"
            ),
            correctAnswerIndex = 1,
            animalName = "Nile Crocodile",
            scientificName = "Crocodylus niloticus",
            category = "River Giants",
            dangerLevel = "The River Gauntlet",
            fascinatingFact = "Nile crocodiles grow over 5 meters (16 ft) and generate a crushing 3,700 psi bite. During the Great Migration, thousands lie in wait along the Mara and Grumeti river crossings.",
            expertCommentaryCorrect = "Deadly and prehistoric! The Mara river crossings are the ultimate drama of the savannah, where ancient crocs test migrating herds.",
            expertCommentaryIncorrect = "Look beneath the muddy ripples! The Nile Crocodile rules the river crossings with patient, explosive stealth.",
            iconType = AnimalIconType.CROCODILE
        ),
        QuizQuestion(
            id = 20,
            question = "True or False: The majestic giraffe is completely harmless and has no natural defenses against predators like lions.",
            options = listOf(
                "True - they can only run away",
                "False - their massive kick can shatter a lion's skull",
                "True - they have zero offensive defenses",
                "False - their horns contain defensive toxins"
            ),
            correctAnswerIndex = 1,
            animalName = "Giraffe",
            scientificName = "Giraffa camelopardalis",
            category = "Gentle Giants",
            dangerLevel = "Skull-Shattering Kick",
            fascinatingFact = "Giraffes possess dinner-plate-sized hooves on long muscular legs that can kick in any direction. A single direct blow has been documented decapitating or killing adult male lions!",
            expertCommentaryCorrect = "Bravo, field naturalist! Giraffes look peaceful browsing atop acacia trees, but their defensive kick is an absolute lethal sledgehammer!",
            expertCommentaryIncorrect = "Don't be fooled by their grace! Giraffes possess a bone-crushing kick capable of shattering a lion's ribs or skull instantly.",
            iconType = AnimalIconType.GIRAFFE
        )
    )
}
