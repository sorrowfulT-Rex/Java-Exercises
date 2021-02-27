package tutorialquestion;// bec2: Music collection
/*
	Write a class, tutorialquestion.Record, to represent records that have been released by music artists.
	An instance of tutorialquestion.Record should consist of a title (a string) and a genre, which should be one of rock,
	pop or jazz. You should use an enumerated type, tutorialquestion.Genre, to represent these.

	Write another class, tutorialquestion.Artist, representing a music artist. An instance of tutorialquestion.Artist should consist of
	a name (a string), a catalogue (a list of Records) and a main genre (a tutorialquestion.Genre).
	The latter is the genre of music that this artist tends to record.
 */

import java.util.ArrayList;
import java.util.List;

public class _bec2 {
}

enum Genre {
	ROCK, POP, JAZZ
}

class Record {
	private final String title;
	private final Genre genre;

	public Record(String title, Genre genre) {
		this.title = title;
		this.genre = genre;
	}

	public Genre getGenre() {
		return genre;
	}
}

class Artist {
	private final String name;
	private List<Record> calalogue = new ArrayList<>();
	private Genre mainGenre;

	public Artist(String name) {
		this.name = name;
	}

	// Add a record of the given name to the artist's catalogue
	// Use the artist's main genre as the record's genre
	public void addRecord(String name) {
		calalogue.add(new Record(name, mainGenre));
	}

	// Add a record of the given name and genre to the artist's
	// catalogue
	public void addRecord(String name, Genre genre) {
		calalogue.add(new Record(name, genre));
	}

	// Display the artist's catalogue
	public void showCatalogue() {
    System.out.println("Catalogue for " + name + ":");
    for (var r : calalogue) {
      System.out.println(r);
    }
	}

	// Display the subset of artist's catalogue that falls into
	// the given genre
	public void showGenre(Genre genre) {
		System.out.println("Catalogue for " + name + " with genre " + genre + ":");
		for (var r : calalogue) {
      if (r.getGenre().equals(genre)) {
        System.out.println(r);
			}
		}
	}
}
