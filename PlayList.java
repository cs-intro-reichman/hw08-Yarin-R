/**
 * Represnts a list of musical tracks. The list has a maximum capacity (int),
 * and an actual size (number of tracks in the list, an int).
 */
class PlayList {
    private Track[] tracks; // Array of tracks (Track objects)
    private int maxSize; // Maximum number of tracks in the array
    private int size; // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */
    public int getMaxSize() {
        return maxSize;
    }

    /** Returns the current number of tracks in this play list. */
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }

    /**
     * Appends the given track to the end of this list.
     * If the list is full, does nothing and returns false.
     * Otherwise, appends the track and returns true.
     */
    public boolean add(Track track) {
        // check if the actual number of tracks is less then the max track number.
        if (size < maxSize) {
            tracks[size] = track; // insert track.
            size++; // update actal number of tracks.
            return true;
        }
        return false;
    }

    /**
     * Returns the data of this list, as a string. Each track appears in a separate
     * line.
     */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder tracks = new StringBuilder(); // declare StringBuilder for an efficient implementation.
        // goes through the tracks.
        for (int i = 0; i < size; i++) {
            tracks.append("\n" + this.tracks[i].toString()); // insert track.
        }
        return tracks.toString();
    }

    /**
     * Removes the last track from this list. If the list is empty, does nothing.
     */
    public void removeLast() {
        // check if the list is empty,
        if (size != 0) {
            tracks[size] = null; // clear the value of the last track.
            size--; // update actual number of tracks.
        }
    }

    /** Returns the total duration (in seconds) of all the tracks in this list. */
    public int totalDuration() {
        int duration = 0; // declare int that will contain total seconds of all songs in tracks.
        // goes through the tracks.
        for (int i = 0; i < size; i++) {
            duration += tracks[i].getDuration(); // add duration of each track.
        }
        return duration;
    }

    /**
     * Returns the index of the track with the given title in this list.
     * If such a track is not found, returns -1.
     */
    public int indexOf(String title) {
        // goes through the tracks.
        for (int i = 0; i < size; i++) {
            // check if the current track is the given title.
            if (tracks[i].getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inserts the given track in index i of this list. For example, if the list is
     * (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     * If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     * If i is negative or greater than the size of this list, or if the list
     * is full, does nothing and returns false. Otherwise, inserts the track and
     * returns true.
     */
    public boolean add(int i, Track track) {
        // check false conditions.
        if ((size == maxSize) || (i > size) || (i < 0)) {
            return false;
        }
        // goes through the tracks from the last to i.
        for (int j = size; j > i; j--) {
            tracks[j] = tracks[j - 1]; // move all of the tracks to the right untill reaching track i.
        }
        tracks[i] = track; // add new track
        size++; // update actual number of tracks
        return true;
    }

    /**
     * Removes the track in the given index from this list.
     * If the list is empty, or the given index is negative or too big for this
     * list,
     * does nothing and returns -1.
     */
    public void remove(int i) {
        // check false conditions.
        if (!((size == 0) || (i < 0) || (i > size))) {
            // goes through the tracks from i.
            for (int j = i; j > size - 1; j++) {
                tracks[j] = tracks[j + 1]; // move all of the tracks to the right untill reaching the last track.
            }
            tracks[size - 1] = null; // remove track at i'th position.
            size--; // Update actual number of tracks
        }
    }

    /**
     * Removes the first track that has the given title from this list.
     * If such a track is not found, or the list is empty, or the given index
     * is negative or too big for this list, does nothing.
     */
    public void remove(String title) {
        // check false conditions.
        if (!(size == 0)) {
            remove(indexOf(title)); // remove given track using its index.
        }

    }

    /**
     * Removes the first track from this list. If the list is empty, does nothing.
     */
    public void removeFirst() {
        remove(0); // remove track in index 0 (first one).
    }

    /**
     * Adds all the tracks in the other list to the end of this list.
     * If the total size of both lists is too large, does nothing.
     */
    //// An elegant and terribly inefficient implementation.
    public void add(PlayList other) {
        // check false conditions.
        if (size + other.getSize() <= maxSize) {
            // goes through the tracks in the other playlist.
            for (int i = 0; i < other.getSize(); i++) {
                add(other.getTrack(i)); // combine both into the original playlist.
            }
        }
    }

    /**
     * Returns the index in this list of the track that has the shortest duration,
     * starting the search in location start. For example, if the durations are
     * 7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the
     * minimum value (5) when starting the search from index 2.
     * If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        // check false conditions.
        if (start < 0 || start > size - 1) {
            return -1;
        }
        int minDuration = start; // declare an int that will contain the min track duration.
        // goes through the tracks.
        for (int i = start + 1; i < size; i++) {
            // find the minimal track duration.
            if (tracks[i].getDuration() < tracks[minDuration].getDuration()) {
                minDuration = i;
            }
        }
        return minDuration;
    }

    /**
     * Returns the title of the shortest track in this list.
     * If the list is empty, returns null.
     */
    public String titleOfShortestTrack() {
        if (!(size == 0)) {
            return tracks[minIndex(0)].getTitle();
        }
        return null;
    }

    /**
     * Sorts this list by increasing duration order: Tracks with shorter
     * durations will appear first. The sort is done in-place. In other words,
     * rather than returning a new, sorted playlist, the method sorts
     * the list on which it was called (this list).
     */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,
        // calling the minIndex method in each iteration.
        for (int i = 0; i < size - 1; i++){
            int minIndex = minIndex(i); // check the min index from i.
            // check if min index is not i.
            if (minIndex != i) {
                Track track = tracks[i]; // save track i.
                tracks[i] = tracks[minIndex]; // change track i to the min track.
                tracks[minIndex] = track; // change the min track to the i'th track.
            }
        }
    }
}
