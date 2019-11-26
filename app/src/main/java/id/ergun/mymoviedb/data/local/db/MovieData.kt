package id.ergun.mymoviedb.data.local.db

import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.model.Movie

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieData {

    fun getMovies(): MutableList<Movie> {
        return getStaticMovies()
    }

    private fun getStaticMovies(): MutableList<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        list.add(
            Movie(
                id = 332562,
                title = "A Star Is Born",
                image = R.drawable.poster_a_star,
                overview = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."
            )
        )
        list.add(
            Movie(
                id = 297802,
                title = "Aquaman",
                image = R.drawable.poster_aquaman,
                overview = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne."
            )
        )
        list.add(
            Movie(
                id = 299536,
                title = "Avengers: Infinity War",
                image = R.drawable.poster_avengerinfinity,
                overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
            )
        )
        list.add(
            Movie(
                id = 405774,
                title = "Bird Box",
                image = R.drawable.poster_birdbox,
                overview = "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety."
            )
        )
        list.add(
            Movie(
                id = 424694,
                title = "Bohemian Rapsody",
                image = R.drawable.poster_bohemian,
                overview = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess."
            )
        )
        list.add(
            Movie(
                id = 424783,
                title = "Bumblebee",
                image = R.drawable.poster_bumblebee,
                overview = "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken.  When Charlie revives him, she quickly learns this is no ordinary yellow VW bug."
            )
        )
        list.add(
            Movie(
                id = 480530,
                title = "Creed II",
                image = R.drawable.poster_creed,
                overview = "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life."
            )
        )
        list.add(
            Movie(
                id = 293660,
                title = "Deadpool",
                image = R.drawable.poster_deadpool,
                overview = "Deadpool tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life."
            )
        )
        list.add(
            Movie(
                id = 166428,
                title = "How to Train Your Dragon: The Hidden World",
                image = R.drawable.poster_dragon,
                overview = "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind."
            )
        )
        list.add(
            Movie(
                id = 503314,
                title = "Dragon Ball Super: Broly",
                image = R.drawable.poster_dragonball,
                overview = "Earth is peaceful following the Tournament of Power. Realizing that the universes still hold many more strong people yet to see, Goku spends all his days training to reach even greater heights. Then one day, Goku and Vegeta are faced by a Saiyan called 'Broly' who they've never seen before. The Saiyans were supposed to have been almost completely wiped out in the destruction of Planet Vegeta, so what's this one doing on Earth? This encounter between the three Saiyans who have followed completely different destinies turns into a stupendous battle, with even Frieza (back from Hell) getting caught up in the mix."
            )
        )
        list.add(
            Movie(
                id = 450465,
                title = "Glass",
                image = R.drawable.poster_glass,
                overview = "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men."
            )
        )
        list.add(
            Movie(
                id = 399402,
                title = "Hunter Killer",
                image = R.drawable.poster_hunterkiller,
                overview = "Captain Glass of the USS Arkansas discovers that a coup d'état is taking place in Russia, so he and his crew join an elite group working on the ground to prevent a war."
            )
        )
        list.add(
            Movie(
                id = 400650,
                title = "Mary Poppins Returns",
                image = R.drawable.poster_marrypopins,
                overview = "In Depression-era London, a now-grown Jane and Michael Banks, along with Michael's three children, are visited by the enigmatic Mary Poppins following a personal loss. Through her unique magical skills, and with the aid of her friend Jack, she helps the family rediscover the joy and wonder missing in their lives."
            )
        )
        list.add(
            Movie(
                id = 428078,
                title = "Mortal Engines",
                image = R.drawable.poster_mortalengine,
                overview = "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever."
            )
        )
        list.add(
            Movie(
                id = 568709,
                title = "Preman Pensiun",
                image = R.drawable.poster_preman,
                overview = "After three years, the business of Muslihat (Epi Kusnandar), who has retired as a thug, has a problem. Sales decline. Muslihat also faces new problems when Safira (Safira Maharani), her only daughter, grows up in adolescence and begins to be visited by boys. A bigger problem: frictions between his former subordinates."
            )
        )
        list.add(
            Movie(
                id = 375588,
                title = "Robin Hood",
                image = R.drawable.poster_robinhood,
                overview = "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown."
            )
        )
        list.add(
            Movie(
                id = 324857,
                title = "Spider-Man: Into the Spider-Verse",
                image = R.drawable.poster_spiderman,
                overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension."
            )
        )
        list.add(
            Movie(
                id = 446807,
                title = "The Girl in the Spider's Web",
                image = R.drawable.poster_thegirl,
                overview = "In Stockholm, Sweden, hacker Lisbeth Salander is hired by Frans Balder, a computer engineer, to retrieve a program that he believes it is too dangerous to exist."
            )
        )
        list.add(
            Movie(
                id = 504172,
                title = "The Mule",
                image = R.drawable.poster_themule,
                overview = "Earl Stone, a man in his eighties, is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he's just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates."
            )
        )
        list.add(
            Movie(
                id = 335983,
                title = "Venom",
                image = R.drawable.poster_venom,
                overview = "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own."
            )
        )
        return list
    }
}