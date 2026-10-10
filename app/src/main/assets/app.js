/**
 * Scariest Animals of the Savannah Quiz - Web Application
 * Hosted by Ranger Kofi, Safari Wildlife Specialist
 */

// 20 Deadliest Animals Questions Database
const QUESTIONS = [
  {
    id: 1,
    question: "Which Savannah animal is considered the most deadly to humans, killing more people annually than lions or elephants?",
    options: ["Hippopotamus", "African Lion", "Nile Crocodile", "African Elephant"],
    correctIndex: 0,
    animalName: "Hippopotamus",
    latinName: "Hippopotamus amphibius",
    emoji: "🦛",
    category: "River Giants",
    dangerLevel: "Territorial Behemoth",
    fact: "Hippos are fiercely territorial and can sprint up to 30 km/h (19 mph) on land. Their canine tusks can reach 50 cm with a crushing 2,000 psi bite!",
    expertCommentCorrect: "Spot on, explorer! Never underestimate a hippo—they can capsize and bite a wooden canoe cleanly in two!",
    expertCommentIncorrect: "A common safari misconception! The lumbering Hippopotamus claims roughly 500 human lives each year along Africa's waterways."
  },
  {
    id: 2,
    question: "What tiny creature is actually the deadliest animal in Africa, responsible for over 500,000 deaths annually?",
    options: ["Tsetse Fly", "Mosquito", "Driver Ant", "African Assassin Bug"],
    correctIndex: 1,
    animalName: "Mosquito (Anopheles)",
    latinName: "Anopheles gambiae",
    emoji: "🦟",
    category: "Microscopic Threats",
    dangerLevel: "Continental Killer",
    fact: "Female Anopheles mosquitoes transmit Plasmodium falciparum malaria parasites, resulting in more human fatalities than every apex savannah predator combined.",
    expertCommentCorrect: "Brilliant deduction! The deadliest beast in the savannah isn't the biggest, but this tiny airborne vector.",
    expertCommentIncorrect: "Look closer to the ground and air! The humble Mosquito is responsible for over half a million deaths across Africa every single year."
  },
  {
    id: 3,
    question: "Which large herbivore is known as one of the 'Big Five' and is famous for being incredibly unpredictable and aggressive, sometimes stalking humans if injured?",
    options: ["Cape Buffalo", "White Rhinoceros", "Common Warthog", "Blue Wildebeest"],
    correctIndex: 0,
    animalName: "Cape Buffalo",
    latinName: "Syncerus caffer",
    emoji: "🐃",
    category: "The Big Five",
    dangerLevel: "The 'Black Death'",
    fact: "Known to hunters as 'The Widowmaker' or 'Black Death', Cape Buffaloes have fused bone helmets ('bosses') that can deflect high-caliber rounds and are famous for ambushing pursuers.",
    expertCommentCorrect: "Sharp instincts! Solitary old bulls ('Dagga Boys') are legendary among game rangers for their relentless cunning.",
    expertCommentIncorrect: "Watch your flank! It's the formidable Cape Buffalo. Unlike domestic cattle, they have never been tamed and will circle back to ambush threats."
  },
  {
    id: 4,
    question: "Which snake is considered the deadliest in Africa due to its immense speed, aggression, and highly toxic venom?",
    options: ["Puff Adder", "Boomslang", "Black Mamba", "Egyptian Cobra"],
    correctIndex: 2,
    animalName: "Black Mamba",
    latinName: "Dendroaspis polylepis",
    emoji: "🐍",
    category: "Venomous Serpents",
    dangerLevel: "Rapid Neurotoxic Lethality",
    fact: "Reaching speeds over 19 km/h (12 mph), a single bite from a Black Mamba injects enough potent neurotoxin and cardiotoxin to kill multiple adult humans within hours.",
    expertCommentCorrect: "Crikey, right on target! Named not for its brownish scales, but for the ink-black lining of its threatening, open mouth!",
    expertCommentIncorrect: "Be cautious! That lethal title belongs to the feared Black Mamba—lightning quick, agile, and packing deadly neurotoxins."
  },
  {
    id: 5,
    question: "Which stealthy predator is considered by some wildlife experts to be the most dangerous big cat to encounter on foot?",
    options: ["Cheetah", "Leopard", "Caracal", "Serval"],
    correctIndex: 1,
    animalName: "Leopard",
    latinName: "Panthera pardus",
    emoji: "🐆",
    category: "Stealth Predators",
    dangerLevel: "Silent Arboreal Ambush",
    fact: "Leopards are supreme ambush masters possessing incredible pound-for-pound strength, capable of hoisting heavy antelope carcasses up into thorny acacia branches to evade hyenas.",
    expertCommentCorrect: "Masterful observation! A leopard will freeze motionlessly and let an unsuspecting walker pass within feet before striking.",
    expertCommentIncorrect: "Look up in the trees! It's the elusive Leopard. Unmatched stealth and climbing power make it the cat rangers fear most on foot patrols."
  },
  {
    id: 6,
    question: "Which snake is responsible for the most human fatalities in Africa, often because it relies on camouflage and lies still until stepped on?",
    options: ["Black Mamba", "Gaboon Viper", "Puff Adder", "Green Mamba"],
    correctIndex: 2,
    animalName: "Puff Adder",
    latinName: "Bitis arietans",
    emoji: "🐍",
    category: "Venomous Serpents",
    dangerLevel: "Camouflaged Cytotoxic Threat",
    fact: "While most snakes flee human footsteps, the thick-bodied Puff Adder freezes in leaf litter using superb camouflage, striking with one of the fastest speeds recorded among vipers when stepped on.",
    expertCommentCorrect: "Top-tier savannah knowledge! The puff adder's reluctance to flee is precisely why it causes the most snakebite casualties in Africa.",
    expertCommentIncorrect: "Watch your step in the grass! It's the Puff Adder. Because they blend perfectly and don't slither away, thousands step on them accidentally."
  },
  {
    id: 7,
    question: "The Nile Crocodile is a fearsome predator. What unique physiological feature allows it to hold its breath for over an hour?",
    options: ["Secondary air-sac lungs", "A specialized four-chambered heart", "Scale oxygen absorption", "Throat gill slits"],
    correctIndex: 1,
    animalName: "Nile Crocodile",
    latinName: "Crocodylus niloticus",
    emoji: "🐊",
    category: "Aquatic Apex",
    dangerLevel: "Prehistoric Ambush",
    fact: "Crocodiles possess a unique 'Foramen of Panizza' valve in their 4-chambered heart that can shunt deoxygenated blood away from the lungs to conserve oxygen during deep submergence.",
    expertCommentCorrect: "Superb anatomical wisdom! This remarkable cardiovascular adaptation allows them to lie submerged like a sunken log waiting for thirsty game.",
    expertCommentIncorrect: "Fascinating anatomy at work! It is their specialized four-chambered heart with a cog-wheel valve that recirculates blood and conserves oxygen underwater."
  },
  {
    id: 8,
    question: "Despite being smaller and often considered scavengers, which animal is actually a highly efficient, intelligent pack hunter with a bite force stronger than a lion?",
    options: ["Black-backed Jackal", "Honey Badger", "Spotted Hyena", "Aardwolf"],
    correctIndex: 2,
    animalName: "Spotted Hyena",
    latinName: "Crocuta crocuta",
    emoji: "🐺",
    category: "Pack Hunters",
    dangerLevel: "Bone-Crushing Clan",
    fact: "Spotted hyenas hunt up to 95% of their own food in complex coordinated clans. Their massive jaws exert over 1,100 psi, effortlessly splintering large zebra and giraffe bones.",
    expertCommentCorrect: "Spot on! Lion-King mythology got it wrong—hyenas are brilliant cooperative hunters, and lions frequently steal kills from them!",
    expertCommentIncorrect: "Don't underestimate the clan! The Spotted Hyena boasts a bite force greater than an adult male lion and hunts the vast majority of its own prey."
  },
  {
    id: 9,
    question: "Which of these large animals has notoriously poor eyesight, making them easily startled and prone to aggressive defensive charges?",
    options: ["Black Rhinoceros", "Plains Zebra", "Blue Wildebeest", "African Lion"],
    correctIndex: 0,
    animalName: "Black Rhinoceros",
    latinName: "Diceros bicornis",
    emoji: "🦏",
    category: "Armored Megafauna",
    dangerLevel: "Blind Kinetic Charge",
    fact: "Black rhinos cannot clearly see a stationary human beyond 30 meters (98 ft). When startled by sound or scent, they charge first at speeds up to 55 km/h (34 mph) and ask questions never!",
    expertCommentCorrect: "Dead right! With hooked lips, sharp horns, and terrible vision, black rhinos will charge termite mounds, bushes, and safari vehicles alike!",
    expertCommentIncorrect: "Careful in the brush! The Black Rhino has notoriously blurry vision and compensates with hyper-aggressive preemptive charges at any strange sound."
  },
  {
    id: 10,
    question: "Which small, fierce animal is known to aggressively defend itself and its food against much larger predators, and is even largely immune to snake venom?",
    options: ["Meerkat", "Honey Badger", "Banded Mongoose", "African Civet"],
    correctIndex: 1,
    animalName: "Honey Badger (Ratel)",
    latinName: "Mellivora capensis",
    emoji: "🦡",
    category: "Fearless Nomads",
    dangerLevel: "Indomitable Brawler",
    fact: "Honey badgers possess rubbery, quarter-inch-thick skin that can withstand porcupine quills and machete cuts. Even after being bitten by a puff adder, they sleep off the venom and wake up eating the snake!",
    expertCommentCorrect: "Guinness World Record holder for most fearless creature! Honey badgers have chased full prides of lions off fresh kills!",
    expertCommentIncorrect: "Never back down! The Honey Badger's rubbery skin and mutated nicotinic acetylcholine receptors make it practically impervious to snake venom."
  },
  {
    id: 11,
    question: "Which insect, known for carrying sleeping sickness, makes certain areas of the savannah virtually uninhabitable for humans and livestock?",
    options: ["Anopheles Mosquito", "Tsetse Fly", "African Army Ant", "Sausage Tree Wasp"],
    correctIndex: 1,
    animalName: "Tsetse Fly",
    latinName: "Glossina morsitans",
    emoji: "🪰",
    category: "Microscopic Threats",
    dangerLevel: "Sleeping Sickness Vector",
    fact: "Tsetse flies bite with serrated mouthparts to feed on vertebrate blood, transmitting protozoan parasites (Trypanosoma) that attack the central nervous system.",
    expertCommentCorrect: "Superb historical ecology knowledge! Tsetse fly zones prevented colonial cattle expansion and preserved vast wilderness areas as national parks.",
    expertCommentIncorrect: "Listen for that buzzing! It is the Tsetse Fly, vector of human sleeping sickness and nagana in livestock across sub-Saharan Africa."
  },
  {
    id: 12,
    question: "What is the primary reason an African Elephant might suddenly become highly aggressive and charge a vehicle or person?",
    options: ["Competition for watering holes", "Protecting their young/calves", "Carnivorous hunting instinct", "Attracted by vehicle engine sounds"],
    correctIndex: 1,
    animalName: "African Bush Elephant",
    latinName: "Loxodonta africana",
    emoji: "🐘",
    category: "The Big Five",
    dangerLevel: "6-Ton Powerhouse",
    fact: "Protective matriarchs and mothers will relentlessly eliminate any perceived danger near a newborn calf. Bull elephants undergoing 'musth' also experience a 60-fold testosterone spike!",
    expertCommentCorrect: "Exactly right! A mother elephant's protective bond is unbreakable. If she tucks her ears back and lowers her head silently, retreat immediately!",
    expertCommentIncorrect: "Family always comes first! Elephants are gentle herbivores, but a mother protecting her calf will overturn trucks without hesitation."
  },
  {
    id: 13,
    question: "Which venomous arachnid found in the savannah has large, powerful pincers but a relatively mild sting compared to others of its kind?",
    options: ["Deathstalker Scorpion", "Emperor Scorpion", "Parabuthus Granulatus", "Spitting Thicktail Scorpion"],
    correctIndex: 1,
    animalName: "Emperor Scorpion",
    latinName: "Pandinus imperator",
    emoji: "🦂",
    category: "Arachnid Predators",
    dangerLevel: "Heavy Claws, Mild Venom",
    fact: "A classic rule in arachnology: scorpions with massive muscular pincers (chelae) rely on physical crushing strength, whereas slender-clawed scorpions carry lethal neurotoxins.",
    expertCommentCorrect: "Top-notch survival tip! Big claws mean little venom punch; skinny claws and a chunky tail mean run for your life!",
    expertCommentIncorrect: "Remember the claw rule! The Emperor Scorpion uses its huge, powerful claws to crush insects, wielding venom no worse than a bee sting."
  },
  {
    id: 14,
    question: "Though rarely attacking humans, which massive bird has a kick powerful enough to kill a lion if threatened?",
    options: ["Kori Bustard", "Secretarybird", "Ostrich", "Martial Eagle"],
    correctIndex: 2,
    animalName: "Common Ostrich",
    latinName: "Struthio camelus",
    emoji: "🦤",
    category: "Avian Giants",
    dangerLevel: "Lethal Talon Kick",
    fact: "An ostrich kicks forward with an estimated force exceeding 2,000 psi, armed with a 4-inch (10 cm) razor-sharp toe claw capable of disemboweling hyenas and lions.",
    expertCommentCorrect: "Bullseye! Standing nearly 9 feet tall and running at 70 km/h, an ostrich is a living dinosaur with a devastating front kick!",
    expertCommentIncorrect: "Don't mock the big bird! An Ostrich's forward kick packs enough power and claw sharpness to crack a lion's skull or pierce vitals."
  },
  {
    id: 15,
    question: "Which wild dog species is one of the most successful predators in Africa, with a hunt success rate of over 80%?",
    options: ["African Wild Dog / Painted Dog", "Black-backed Jackal", "Bat-eared Fox", "Ethiopian Wolf"],
    correctIndex: 0,
    animalName: "African Wild Dog (Painted Dog)",
    latinName: "Lycaon pictus",
    emoji: "🐕",
    category: "Coordinated Packs",
    dangerLevel: "Relentless Marathon Hunters",
    fact: "African Wild Dogs hunt through extraordinary pack communication and endurance, pursuing antelope at 60 km/h over miles. Their 80%+ kill rate easily surpasses lions (25%)!",
    expertCommentCorrect: "Magnificent answer! Their tri-colored coats are unique as fingerprints, and their pack altruism caring for pups and injured adults is unmatched.",
    expertCommentIncorrect: "Teamwork makes the hunt! It's the African Wild Dog (Painted Dog). Their marathon endurance gives them an unmatched 80%+ success rate."
  },
  {
    id: 16,
    question: "The boomslang is a highly venomous snake. Unlike many other venomous snakes that have fangs at the front, where are the boomslang's fangs located?",
    options: ["The roof of the palate", "The back of the jaw / rear-fanged", "Lower jaw tip", "Retractable beneath the tongue"],
    correctIndex: 1,
    animalName: "Boomslang",
    latinName: "Dispholidus typus",
    emoji: "🌿",
    category: "Venomous Serpents",
    dangerLevel: "Potent Hemotoxic Rear-Fangs",
    fact: "Boomslangs are opisthoglyphous (rear-fanged). They can dislocate their jaws up to 170 degrees to bite, delivering hemotoxins that disable blood clotting and cause severe internal bleeding.",
    expertCommentCorrect: "Outstanding herpetology knowledge! Their name means 'tree snake' in Afrikaans, and their venom was once thought harmless until famous cases proved its extreme lethality.",
    expertCommentIncorrect: "Watch the branches! The Boomslang is rear-fanged. It must chew with the back of its jaw to pump its slow-acting, devastating hemotoxin."
  },
  {
    id: 17,
    question: "What large primate, generally peaceful, can become incredibly dangerous if it feels its troop is threatened, displaying immense strength?",
    options: ["Chacma Baboon", "Vervet Monkey", "Blue Monkey", "Mohol Bushbaby"],
    correctIndex: 0,
    animalName: "Chacma Baboon",
    latinName: "Papio ursinus",
    emoji: "🐒",
    category: "Troop Defenders",
    dangerLevel: "5cm Canine Powerhouse",
    fact: "Adult male baboons have dagger-like canines measuring up to 5 cm (2 inches)—longer than a leopard's! Coalitions of males will fearlessly charge leopards to protect their troop.",
    expertCommentCorrect: "Spot on! When alarmed, the troop bark reverberates across the cliffs, and a mob of large males can rip apart threatening predators.",
    expertCommentIncorrect: "Don't underestimate the troop! The Chacma Baboon has massive canine teeth and immense muscular strength when defending its family."
  },
  {
    id: 18,
    question: "Which big cat relies primarily on sheer speed to catch its prey, making it less of an ambush threat to humans but deadly to small gazelles?",
    options: ["Cheetah", "Leopard", "Serval", "Caracal"],
    correctIndex: 0,
    animalName: "Cheetah",
    latinName: "Acinonyx jubatus",
    emoji: "🐆",
    category: "Velocity Sprinters",
    dangerLevel: "High-Speed Specialist",
    fact: "Cheetahs can accelerate from 0 to 97 km/h (60 mph) in just 3 seconds, using enlarged nasal passages, deep chests, and non-retractable claws that grip the soil like running spikes.",
    expertCommentCorrect: "Swift as the wind! Because of their lightweight build and small jaw muscles, wild cheetahs almost never attack humans and avoid confrontations.",
    expertCommentIncorrect: "Think of pure acceleration! The Cheetah is built for open-plains velocity rather than ambush power, posing virtually zero threat to humans."
  },
  {
    id: 19,
    question: "What aquatic animal, alongside the hippo, makes crossing African rivers incredibly dangerous due to its ambush tactics?",
    options: ["African Tigerfish", "Nile Crocodile", "Goliath Bullfrog", "Electric Catfish"],
    correctIndex: 1,
    animalName: "Nile Crocodile",
    latinName: "Crocodylus niloticus",
    emoji: "🐊",
    category: "River Giants",
    dangerLevel: "The River Gauntlet",
    fact: "Nile crocodiles grow over 5 meters (16 ft) and generate a crushing 3,700 psi bite. During the Great Migration, thousands lie in wait along the Mara and Grumeti river crossings.",
    expertCommentCorrect: "Deadly and prehistoric! The Mara river crossings are the ultimate drama of the savannah, where ancient crocs test migrating herds.",
    expertCommentIncorrect: "Look beneath the muddy ripples! The Nile Crocodile rules the river crossings with patient, explosive stealth."
  },
  {
    id: 20,
    question: "True or False: The majestic giraffe is completely harmless and has no natural defenses against predators like lions.",
    options: ["True - they can only run away", "False - their massive kick can shatter a lion's skull", "True - they have zero offensive defenses", "False - their horns contain defensive toxins"],
    correctIndex: 1,
    animalName: "Giraffe",
    latinName: "Giraffa camelopardalis",
    emoji: "🦒",
    category: "Gentle Giants",
    dangerLevel: "Skull-Shattering Kick",
    fact: "Giraffes possess dinner-plate-sized hooves on long muscular legs that can kick in any direction. A single direct blow has been documented decapitating or killing adult male lions!",
    expertCommentCorrect: "Bravo, field naturalist! Giraffes look peaceful browsing atop acacia trees, but their defensive kick is an absolute lethal sledgehammer!",
    expertCommentIncorrect: "Don't be fooled by their grace! Giraffes possess a bone-crushing kick capable of shattering a lion's ribs or skull instantly."
  }
];

// Web Audio Synthesizer
class SoundController {
  constructor() {
    this.enabled = true;
    this.ctx = null;
  }

  initCtx() {
    if (!this.ctx) {
      const AudioCtx = window.AudioContext || window.webkitAudioContext;
      if (AudioCtx) this.ctx = new AudioCtx();
    }
    if (this.ctx && this.ctx.state === 'suspended') {
      this.ctx.resume();
    }
  }

  playTone(freq, type, duration, delay = 0) {
    if (!this.enabled) return;
    this.initCtx();
    if (!this.ctx) return;

    setTimeout(() => {
      try {
        const osc = this.ctx.createOscillator();
        const gain = this.ctx.createGain();
        osc.type = type;
        osc.frequency.setValueAtTime(freq, this.ctx.currentTime);

        gain.gain.setValueAtTime(0.18, this.ctx.currentTime);
        gain.gain.exponentialRampToValueAtTime(0.001, this.ctx.currentTime + duration);

        osc.connect(gain);
        gain.connect(this.ctx.destination);

        osc.start();
        osc.stop(this.ctx.currentTime + duration);
      } catch (e) {
        console.warn('Audio error', e);
      }
    }, delay * 1000);
  }

  playCorrect() {
    this.playTone(523.25, 'sine', 0.12, 0);     // C5
    this.playTone(659.25, 'sine', 0.22, 0.12);  // E5
    this.playTone(783.99, 'sine', 0.35, 0.24);  // G5
  }

  playIncorrect() {
    this.playTone(220.00, 'sawtooth', 0.15, 0);   // A3
    this.playTone(185.00, 'sawtooth', 0.25, 0.14);// F#3
  }

  playTick() {
    this.playTone(880.00, 'triangle', 0.04, 0);
  }

  playTimeExpired() {
    this.playTone(200.00, 'square', 0.18, 0);
    this.playTone(160.00, 'square', 0.28, 0.2);
  }

  playFanfare() {
    this.playTone(523.25, 'triangle', 0.15, 0);
    this.playTone(659.25, 'triangle', 0.15, 0.15);
    this.playTone(783.99, 'triangle', 0.2, 0.3);
    this.playTone(1046.50, 'triangle', 0.45, 0.48);
  }
}

// App State
const state = {
  currentQuestionIndex: 0,
  score: 0,
  timerLimit: 25, // default 25s
  remainingTime: 25,
  timerInterval: null,
  isAnswerSubmitted: false,
  userAnswers: [] // { questionId, selectedIndex, isCorrect, timeSpent }
};

const sound = new SoundController();

// DOM Elements
const introScreen = document.getElementById('intro-screen');
const quizScreen = document.getElementById('quiz-screen');
const resultScreen = document.getElementById('result-screen');

const startBtn = document.getElementById('start-btn');
const audioToggleBtn = document.getElementById('audio-toggle-btn');
const audioIcon = document.getElementById('audio-icon');

// Quiz Screen Elements
const qNumberDisplay = document.getElementById('q-number-display');
const quizProgressBar = document.getElementById('quiz-progress-bar');
const scoreCounter = document.getElementById('score-counter');
const timerPill = document.getElementById('timer-pill');
const timerText = document.getElementById('timer-text');
const timerCircleProgress = document.getElementById('timer-circle-progress');

const animalAvatarBox = document.getElementById('animal-avatar-box');
const animalNameText = document.getElementById('animal-name-text');
const animalLatinText = document.getElementById('animal-latin-text');
const animalDangerTag = document.getElementById('animal-danger-tag');
const animalCategoryTag = document.getElementById('animal-category-tag');
const questionText = document.getElementById('question-text');
const optionsContainer = document.getElementById('options-container');

// Feedback Card Elements
const feedbackCard = document.getElementById('feedback-card');
const feedbackIcon = document.getElementById('feedback-icon');
const feedbackTitle = document.getElementById('feedback-title');
const feedbackSubtitle = document.getElementById('feedback-subtitle');
const feedbackScoreVal = document.getElementById('feedback-score-val');
const correctRevealBox = document.getElementById('correct-reveal-box');
const revealText = document.getElementById('reveal-text');
const factAnimalTitle = document.getElementById('fact-animal-title');
const factContent = document.getElementById('fact-content');
const expertReactionAvatar = document.getElementById('expert-reaction-avatar');
const expertReactionText = document.getElementById('expert-reaction-text');
const nextQuestionBtn = document.getElementById('next-question-btn');
const nextBtnLabel = document.getElementById('next-btn-label');

// Results Screen Elements
const resultPercentageText = document.getElementById('result-percentage-text');
const resultScoreDetail = document.getElementById('result-score-detail');
const resultCircleProgress = document.getElementById('result-circle-progress');
const rankBadgeEmoji = document.getElementById('rank-badge-emoji');
const rankTitleText = document.getElementById('rank-title-text');
const rankSubtitleText = document.getElementById('rank-subtitle-text');
const rankDescriptionText = document.getElementById('rank-description-text');
const statCorrectCount = document.getElementById('stat-correct-count');
const statAccuracyPct = document.getElementById('stat-accuracy-pct');
const statPointsEarned = document.getElementById('stat-points-earned');
const expertResultSpeech = document.getElementById('expert-result-speech');
const retakeBtn = document.getElementById('retake-btn');
const backCampBtn = document.getElementById('back-camp-btn');
const fieldGuideList = document.getElementById('field-guide-list');
const filterCorrectNum = document.getElementById('filter-correct-num');
const filterMissedNum = document.getElementById('filter-missed-num');

// Canvas Renderers
function drawSavannahBanner(canvasId) {
  const canvas = document.getElementById(canvasId);
  if (!canvas) return;
  const ctx = canvas.getContext('2d');
  const w = canvas.width;
  const h = canvas.height;

  // Sky Sunset Gradient
  const skyGrad = ctx.createLinearGradient(0, 0, 0, h);
  skyGrad.addColorStop(0, '#8B2500');
  skyGrad.addColorStop(0.35, '#E65100');
  skyGrad.addColorStop(0.65, '#FF9800');
  skyGrad.addColorStop(0.9, '#FFB74D');
  skyGrad.addColorStop(1, '#2E1505');
  ctx.fillStyle = skyGrad;
  ctx.fillRect(0, 0, w, h);

  // Glowing Sun
  const sunX = w * 0.72;
  const sunY = h * 0.52;
  const sunRadius = h * 0.24;

  const glowGrad = ctx.createRadialGradient(sunX, sunY, 5, sunX, sunY, sunRadius * 1.6);
  glowGrad.addColorStop(0, 'rgba(255, 249, 196, 0.9)');
  glowGrad.addColorStop(0.4, 'rgba(255, 213, 79, 0.6)');
  glowGrad.addColorStop(1, 'rgba(255, 143, 0, 0)');
  ctx.fillStyle = glowGrad;
  ctx.beginPath();
  ctx.arc(sunX, sunY, sunRadius * 1.6, 0, Math.PI * 2);
  ctx.fill();

  ctx.fillStyle = '#FFF9C4';
  ctx.beginPath();
  ctx.arc(sunX, sunY, sunRadius * 0.7, 0, Math.PI * 2);
  ctx.fill();

  // Distant Low Hills
  ctx.fillStyle = '#331604';
  ctx.beginPath();
  ctx.moveTo(0, h * 0.75);
  ctx.bezierCurveTo(w * 0.3, h * 0.7, w * 0.6, h * 0.8, w, h * 0.73);
  ctx.lineTo(w, h);
  ctx.lineTo(0, h);
  ctx.closePath();
  ctx.fill();

  // Foreground Ground
  ctx.fillStyle = '#160802';
  ctx.beginPath();
  ctx.moveTo(0, h * 0.84);
  ctx.bezierCurveTo(w * 0.35, h * 0.87, w * 0.7, h * 0.82, w, h * 0.86);
  ctx.lineTo(w, h);
  ctx.lineTo(0, h);
  ctx.closePath();
  ctx.fill();

  // Acacia Tree Silhouette
  const treeX = w * 0.22;
  const treeY = h * 0.86;
  ctx.fillStyle = '#100501';

  // Trunk
  ctx.beginPath();
  ctx.moveTo(treeX - 5, treeY);
  ctx.quadraticCurveTo(treeX - 6, h * 0.55, treeX - 2, h * 0.35);
  ctx.lineTo(treeX + 2, h * 0.35);
  ctx.quadraticCurveTo(treeX + 5, h * 0.55, treeX + 5, treeY);
  ctx.closePath();
  ctx.fill();

  // Flat Umbrella Canopies
  function drawCanopy(cx, cy, rx, ry) {
    ctx.beginPath();
    ctx.ellipse(cx, cy, rx, ry, 0, 0, Math.PI * 2);
    ctx.fill();
  }

  drawCanopy(treeX - 35, h * 0.32, 55, 14);
  drawCanopy(treeX - 15, h * 0.26, 42, 11);
  drawCanopy(treeX + 25, h * 0.34, 34, 10);

  // Distant Acacia on right
  const smallTreeX = w * 0.88;
  const smallTreeY = h * 0.84;
  ctx.fillStyle = '#220d04';
  ctx.fillRect(smallTreeX - 2, smallTreeY - 24, 4, 24);
  drawCanopy(smallTreeX, smallTreeY - 24, 28, 7);

  // Birds in flight
  function drawBird(bx, by, size) {
    ctx.strokeStyle = '#180702';
    ctx.lineWidth = 1.6 * size;
    ctx.lineCap = 'round';
    ctx.beginPath();
    ctx.moveTo(bx - 6 * size, by + 2 * size);
    ctx.quadraticCurveTo(bx - 3 * size, by - 4 * size, bx, by);
    ctx.quadraticCurveTo(bx + 3 * size, by - 4 * size, bx + 6 * size, by + 2 * size);
    ctx.stroke();
  }

  drawBird(w * 0.44, h * 0.35, 1.0);
  drawBird(w * 0.50, h * 0.27, 0.8);
  drawBird(w * 0.56, h * 0.38, 0.65);
}

// Timer Controller
function startQuestionTimer() {
  clearInterval(state.timerInterval);
  if (state.timerLimit <= 0) {
    // Untimed mode
    timerPill.classList.remove('critical');
    timerText.textContent = "Untimed";
    timerCircleProgress.setAttribute('stroke-dasharray', `100, 100`);
    return;
  }

  state.remainingTime = state.timerLimit;
  updateTimerUI();

  state.timerInterval = setInterval(() => {
    state.remainingTime--;
    updateTimerUI();

    if (state.remainingTime <= 5 && state.remainingTime > 0) {
      sound.playTick();
    }

    if (state.remainingTime <= 0) {
      clearInterval(state.timerInterval);
      handleTimeExpired();
    }
  }, 1000);
}

function updateTimerUI() {
  if (state.timerLimit <= 0) return;
  timerText.textContent = `${state.remainingTime}s`;
  const pct = Math.max(0, (state.remainingTime / state.timerLimit) * 100);
  timerCircleProgress.setAttribute('stroke-dasharray', `${pct}, 100`);

  if (state.remainingTime <= 5) {
    timerPill.classList.add('critical');
  } else {
    timerPill.classList.remove('critical');
  }
}

// Question Presentation
function loadQuestion(index) {
  state.currentQuestionIndex = index;
  state.isAnswerSubmitted = false;

  const q = QUESTIONS[index];

  // Update header tracker
  qNumberDisplay.textContent = index + 1;
  const progressPct = ((index + 1) / QUESTIONS.length) * 100;
  quizProgressBar.style.width = `${progressPct}%`;
  scoreCounter.textContent = state.score;

  // Update animal card
  animalAvatarBox.textContent = q.emoji;
  animalNameText.textContent = q.animalName;
  animalLatinText.textContent = q.latinName;
  animalDangerTag.textContent = `⚠️ ${q.dangerLevel}`;
  animalCategoryTag.textContent = `Habitat: ${q.category}`;

  // Update question prompt
  questionText.textContent = q.question;

  // Build 4 multiple choice options
  optionsContainer.innerHTML = '';
  q.options.forEach((optText, i) => {
    const btn = document.createElement('button');
    btn.className = 'option-btn';
    btn.dataset.index = i;

    const letter = ['A', 'B', 'C', 'D'][i] || (i + 1);
    btn.innerHTML = `
      <span class="option-letter">${letter}</span>
      <span class="option-text">${optText}</span>
    `;

    btn.addEventListener('click', () => selectAnswer(i));
    optionsContainer.appendChild(btn);
  });

  // Hide feedback card
  feedbackCard.classList.add('hidden');
  feedbackCard.classList.remove('correct-feedback', 'incorrect-feedback');
  correctRevealBox.classList.add('hidden');

  // Next button label
  if (index === QUESTIONS.length - 1) {
    nextBtnLabel.textContent = "Complete Expedition & View Rank";
  } else {
    nextBtnLabel.textContent = "Next Question";
  }

  // Start countdown timer
  startQuestionTimer();
}

function selectAnswer(selectedIndex) {
  if (state.isAnswerSubmitted) return;
  state.isAnswerSubmitted = true;
  clearInterval(state.timerInterval);

  const q = QUESTIONS[state.currentQuestionIndex];
  const isCorrect = selectedIndex === q.correctIndex;
  const timeSpent = state.timerLimit > 0 ? (state.timerLimit - state.remainingTime) : 5;

  // Record user answer
  state.userAnswers.push({
    questionId: q.id,
    selectedIndex,
    isCorrect,
    timeSpent
  });

  if (isCorrect) {
    state.score += 5;
    sound.playCorrect();
  } else {
    sound.playIncorrect();
  }

  // Highlight option cards
  const optionButtons = optionsContainer.querySelectorAll('.option-btn');
  optionButtons.forEach((btn, idx) => {
    btn.disabled = true;
    if (idx === q.correctIndex) {
      btn.classList.add('correct');
    } else if (idx === selectedIndex) {
      btn.classList.add('incorrect');
    }
  });

  displayFeedback(isCorrect, false);
}

function handleTimeExpired() {
  if (state.isAnswerSubmitted) return;
  state.isAnswerSubmitted = true;

  sound.playTimeExpired();
  const q = QUESTIONS[state.currentQuestionIndex];

  state.userAnswers.push({
    questionId: q.id,
    selectedIndex: -1,
    isCorrect: false,
    timeSpent: state.timerLimit
  });

  const optionButtons = optionsContainer.querySelectorAll('.option-btn');
  optionButtons.forEach((btn, idx) => {
    btn.disabled = true;
    if (idx === q.correctIndex) {
      btn.classList.add('correct');
    }
  });

  displayFeedback(false, true);
}

function displayFeedback(isCorrect, isTimeExpired) {
  const q = QUESTIONS[state.currentQuestionIndex];

  scoreCounter.textContent = state.score;
  feedbackScoreVal.textContent = state.score;

  feedbackCard.classList.remove('hidden');

  if (isCorrect) {
    feedbackCard.classList.add('correct-feedback');
    feedbackIcon.textContent = "✓";
    feedbackTitle.textContent = "CORRECT! +5 POINTS";
    feedbackSubtitle.textContent = "Outstanding wildlife survival instinct!";
    expertReactionAvatar.textContent = "🤠🎉";
    expertReactionText.textContent = q.expertCommentCorrect;
    correctRevealBox.classList.add('hidden');
  } else {
    feedbackCard.classList.add('incorrect-feedback');
    feedbackIcon.textContent = isTimeExpired ? "⏱️" : "✕";
    feedbackTitle.textContent = isTimeExpired ? "TIME EXPIRED! +0 POINTS" : "INCORRECT! +0 POINTS";
    feedbackSubtitle.textContent = isTimeExpired ? "Too slow! The beast got away!" : "Danger in the brush! Watch your step!";
    expertReactionAvatar.textContent = "🧐⚠️";
    expertReactionText.textContent = q.expertCommentIncorrect;

    // Show correct answer reveal
    correctRevealBox.classList.remove('hidden');
    revealText.textContent = q.options[q.correctIndex];
  }

  factAnimalTitle.textContent = `Fascinating Fact (${q.animalName})`;
  factContent.textContent = q.fact;

  // Auto scroll feedback card into view
  setTimeout(() => {
    feedbackCard.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
  }, 100);
}

function nextQuestion() {
  if (state.currentQuestionIndex < QUESTIONS.length - 1) {
    loadQuestion(state.currentQuestionIndex + 1);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  } else {
    showResults();
  }
}

// Final Results & Rank Calculation
function showResults() {
  clearInterval(state.timerInterval);
  sound.playFanfare();

  quizScreen.classList.remove('active');
  resultScreen.classList.add('active');
  drawSavannahBanner('result-canvas');
  window.scrollTo({ top: 0, behavior: 'smooth' });

  const totalPoints = state.score;
  const percentage = Math.round((totalPoints / 100) * 100);
  const correctCount = state.userAnswers.filter(a => a.isCorrect).length;

  resultPercentageText.textContent = `${percentage}%`;
  resultScoreDetail.textContent = `${totalPoints} / 100 pts`;
  resultCircleProgress.setAttribute('stroke-dasharray', `${percentage}, 100`);

  statCorrectCount.textContent = `${correctCount} / ${QUESTIONS.length}`;
  statAccuracyPct.textContent = `${percentage}%`;
  statPointsEarned.textContent = `${totalPoints}`;

  // Fun Rank assignment:
  // 0-30%: "Savannah Tourist"
  // 31-70%: "Ranger Trainee"
  // 71-100%: "Apex Predator"
  if (percentage <= 30) {
    rankBadgeEmoji.textContent = "🏕️";
    rankTitleText.textContent = "Savannah Tourist";
    rankSubtitleText.textContent = "Stick to the Safari Jeep!";
    rankDescriptionText.textContent = "You survived the tour, but you might want to review the field guide before stepping into the bush. The savannah has no mercy for casual wanderers!";
    expertResultSpeech.textContent = "Phew, that was a close shave with the predators! Don't fret—the savannah is a harsh teacher. Review the facts below and take another run at the expedition!";
  } else if (percentage <= 70) {
    rankBadgeEmoji.textContent = "🧭";
    rankTitleText.textContent = "Ranger Trainee";
    rankSubtitleText.textContent = "Promising Bush Instincts!";
    rankDescriptionText.textContent = "Impressive bushcraft! You know which watering holes to avoid and how to spot a charging buffalo. A few more expeditions and you'll lead your own patrol.";
    expertResultSpeech.textContent = "Well done, trainee! You have solid instincts and respected the big beasts. Review the questions you missed below to sharpen your edge!";
  } else {
    rankBadgeEmoji.textContent = "🦁";
    rankTitleText.textContent = "Apex Predator";
    rankSubtitleText.textContent = "Master of the Savannah!";
    rankDescriptionText.textContent = "Magnificent! You possess legendary wildlife wisdom. You outsmarted the deadliest snakes, crocodiles, and megafauna. The entire savannah bows to your knowledge!";
    expertResultSpeech.textContent = "Magnificent work, explorer! You identified the dangerous habits of Africa's deadliest creatures with master precision. You're ready to guide a patrol across the Serengeti!";
  }

  // Update filter counters
  filterCorrectNum.textContent = correctCount;
  filterMissedNum.textContent = QUESTIONS.length - correctCount;

  renderFieldGuide('all');
}

// Field Guide Review List
function renderFieldGuide(filter = 'all') {
  fieldGuideList.innerHTML = '';

  QUESTIONS.forEach((q, idx) => {
    const record = state.userAnswers.find(a => a.questionId === q.id);
    const isCorrect = record && record.isCorrect;

    if (filter === 'correct' && !isCorrect) return;
    if (filter === 'missed' && isCorrect) return;

    const userSelectedIdx = record ? record.selectedIndex : -1;
    const userSelectedText = userSelectedIdx >= 0 ? q.options[userSelectedIdx] : "Time expired (No answer)";

    const card = document.createElement('div');
    card.className = `review-item ${isCorrect ? 'correct' : 'missed'}`;

    card.innerHTML = `
      <div class="review-header">
        <span class="review-badge ${isCorrect ? 'correct' : 'missed'}">${isCorrect ? '✓' : '✕'}</span>
        <div class="review-title-group">
          <span class="review-title">${q.emoji} Q${q.id}: ${q.animalName}</span>
          <span class="review-pts-tag ${isCorrect ? 'correct' : 'missed'}">${isCorrect ? '+5 pts' : '+0 pts'}</span>
        </div>
        <span class="review-toggle-icon">▼</span>
      </div>
      <p class="review-prompt">${q.question}</p>
      <div class="review-details hidden">
        <div class="review-user-ans">Your answer: <strong>${userSelectedText}</strong></div>
        ${!isCorrect ? `<div class="review-correct-ans">Correct answer: <strong>${q.options[q.correctIndex]}</strong></div>` : ''}
        <div class="review-fact-box">
          <strong>Fascinating Fact:</strong> ${q.fact}
        </div>
      </div>
    `;

    card.addEventListener('click', () => {
      const details = card.querySelector('.review-details');
      const toggleIcon = card.querySelector('.review-toggle-icon');
      const isClosed = details.classList.contains('hidden');
      if (isClosed) {
        details.classList.remove('hidden');
        toggleIcon.textContent = '▲';
      } else {
        details.classList.add('hidden');
        toggleIcon.textContent = '▼';
      }
    });

    fieldGuideList.appendChild(card);
  });
}

// Event Listeners
startBtn.addEventListener('click', () => {
  sound.initCtx();
  introScreen.classList.remove('active');
  quizScreen.classList.add('active');
  state.score = 0;
  state.userAnswers = [];
  loadQuestion(0);
  window.scrollTo({ top: 0, behavior: 'smooth' });
});

nextQuestionBtn.addEventListener('click', nextQuestion);

retakeBtn.addEventListener('click', () => {
  resultScreen.classList.remove('active');
  quizScreen.classList.add('active');
  state.score = 0;
  state.userAnswers = [];
  loadQuestion(0);
  window.scrollTo({ top: 0, behavior: 'smooth' });
});

backCampBtn.addEventListener('click', () => {
  resultScreen.classList.remove('active');
  introScreen.classList.add('active');
  drawSavannahBanner('hero-canvas');
  window.scrollTo({ top: 0, behavior: 'smooth' });
});

// Sound Toggle
audioToggleBtn.addEventListener('click', () => {
  sound.enabled = !sound.enabled;
  audioIcon.textContent = sound.enabled ? '🔊' : '🔇';
});

// Timer Settings Chips
const timerChips = document.querySelectorAll('#timer-chips .chip');
timerChips.forEach(chip => {
  chip.addEventListener('click', () => {
    timerChips.forEach(c => c.classList.remove('active'));
    chip.classList.add('active');
    state.timerLimit = parseInt(chip.dataset.timer, 10);
  });
});

// Review Filter Chips
const filterChips = document.querySelectorAll('.review-filter-chips .filter-chip');
filterChips.forEach(chip => {
  chip.addEventListener('click', () => {
    filterChips.forEach(c => c.classList.remove('active'));
    chip.classList.add('active');
    renderFieldGuide(chip.dataset.filter);
  });
});

// Initial Setup
window.addEventListener('DOMContentLoaded', () => {
  drawSavannahBanner('hero-canvas');
});
window.addEventListener('resize', () => {
  if (introScreen.classList.contains('active')) drawSavannahBanner('hero-canvas');
  if (resultScreen.classList.contains('active')) drawSavannahBanner('result-canvas');
});
