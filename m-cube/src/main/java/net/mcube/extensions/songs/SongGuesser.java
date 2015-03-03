/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

/*
 * Created on 6-gen-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.extensions.songs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.della.stuff.generic.file.FileUtils;



/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SongGuesser {

    private Pattern trackPattern;

    private Pattern yearPattern;

    private String fileSeparator;

    private String firstDataSeparator;

    private String secondDataSeparator;

    private String thirdDataSeparator;

    private String fourthDataSeparator;

    private List separatorsList;

    private String chosenSeparator;

    private String filename;

    private File file;

    private static final int MAX_ACCEPTABLE_OCCURRENCIES = 5;

    public SongGuesser() {
        trackPattern = Pattern.compile("\\d{1,2}");
        yearPattern = Pattern.compile("\\d{1,4}");
        fileSeparator = System.getProperty("file.separator");
        firstDataSeparator = "-";
        secondDataSeparator = "_";
        thirdDataSeparator = ".";
        fourthDataSeparator = " ";
        separatorsList = new LinkedList();
        separatorsList.add(firstDataSeparator);
        separatorsList.add(secondDataSeparator);
        separatorsList.add(thirdDataSeparator);
        // separatorsList.add(fourthDataSeparator);
    }

    public void setFile(File file) {
        this.file = file;
        filename = FileUtils.fileNameWithNoExtension(file);
        // Iterator it = separatorsList.iterator();
        // String separator = (String) it.next();

        chosenSeparator = chooseSeparator();

    }

    private String chooseSeparator() {
        String choice = "";
        int bestOccurNumber = 0;
        for (Iterator it = separatorsList.iterator(); it.hasNext();) {
            String sep = (String) it.next();
            int occurrencies = countOccurrencies(sep);
            if (occurrencies > bestOccurNumber && occurrencies <= MAX_ACCEPTABLE_OCCURRENCIES) {
                choice = sep;
                bestOccurNumber = occurrencies;
            }
        }
        return choice;
    }

    private int countOccurrencies(String sep) {
        int index = 0;
        int count = 0;
        while (index != -1) {
            index = filename.indexOf(sep, index + 1);
            count++;
        }
        return count;
    }

    public String guessArtist() {

        String guess = "";
        guess = guessArtistFromFilename();
        if (guess != "")
            return guess;
        String path = file.getParent();
        StringTokenizer st = new StringTokenizer(path, fileSeparator);
        int max = st.countTokens();
        for (int i = 0; i < max - 1; i++) {
            guess = st.nextToken();
        }
        return guess;
    }

    private String guessArtistFromFilename() {
        StringTokenizer st = new StringTokenizer(filename, chosenSeparator);
        if (st.countTokens() > 4)
            return "";
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (isUsefulString(token)) {
                sb.append(token);
                sb.append("-");
            }
        }

        st = new StringTokenizer(sb.toString(), "-");
        if (st.countTokens() <= 1)
            return "";

        // TODO dovrebbe controllare se � uguale all'album
        return st.nextToken().trim();
    }

    private boolean isUsefulString(String token) {
        Matcher tm = trackPattern.matcher(token);
        Matcher ym = yearPattern.matcher(token);
        if (tm.matches() || ym.matches())
            return false;
        if (token.length() <= 1)
            return false;
        return true;
    }

    public String guessTitle() {
        String guess = guessTitleFromFilename();
        if (guess != "")
            return guess;

        return filename;
    }

    private String guessTitleFromFilename() {
        StringTokenizer st = new StringTokenizer(filename, chosenSeparator);
        if (st.countTokens() <= 1)
            return "";
        if (st.countTokens() > 4)
            return "";
        String token = "";
        while (st.hasMoreTokens())
            token = st.nextToken();
        return token.trim();
    }

    public String guessTrack(File mp3) {
        String guess = guessTrackFromFilename();
        if (guess != "")
            return guess;

//        guessFromEventualPlaylist(mp3);

        return "";

    }

    private void guessFromEventualPlaylist(File mp3) {
        File folder = new File(mp3.getParent());
        List listFiles = FileUtils.listFiles(folder, net.della.stuff.generic.file.M3UFileFilter);
        for (Iterator iter = listFiles.iterator(); iter.hasNext();) {
            File m3uFile = (File) iter.next();
            Playlist playlist = null;
            playlist = loadPlaylist(m3uFile, Playlist.WINAMP_FORMAT);
            if (playlist == null)
                playlist = loadPlaylist(m3uFile, Playlist.MUSICMATCH_FORMAT);
            if (playlist == null)
                return;
            for (int i = 0; i < playlist.size(); i++) {
                Object object = playlist.get(i);
            }

        }
    }

    private Playlist loadPlaylist(File m3uFile, int playlistType) {
        Playlist playlist = null;
        try {
            playlist = new Playlist();
            playlist.loadFromFile(m3uFile, playlistType);

        } catch (PlaylistException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return playlist;

    }

    private String guessTrackFromFilename() {
        StringTokenizer st = new StringTokenizer(filename, chosenSeparator);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            Matcher m = trackPattern.matcher(token);
            if (m.matches()) {
                return token;
            }
        }
        return "";
    }

    public String guessAlbum() {

        String guess = "";

//        guess = guessAlbumFromFilename();
//        if (guess != "")
//            return guess;

        if (guess == "") {
            String path = file.getParent();
            StringTokenizer st = new StringTokenizer(path, fileSeparator);
            int max = st.countTokens();
            for (int i = 0; i < max; i++) {
                guess = st.nextToken();
            }
            return guess;
        }
        return "";
    }

    private String guessAlbumFromFilenameOld() {
        StringTokenizer st = new StringTokenizer(filename, chosenSeparator);
        int countTokens = st.countTokens();
        if (countTokens <= 3)
            return "";
        st.nextToken();

        String token = st.nextToken().trim();
        if (isUsefulString(token))
            return token;

        return "";
    }

    private String guessAlbumFromFilename() {
        StringTokenizer st = new StringTokenizer(filename, chosenSeparator);
        if (st.countTokens() > 4)
            return "";
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (isUsefulString(token)) {
                sb.append(token);
                sb.append("-");
            }
        }

        st = new StringTokenizer(sb.toString(), "-");
        if (st.countTokens() <= 2)
            return "";

        // TODO dovrebbe controllare se � uguale all'album
        st.nextToken();
        return st.nextToken().trim();
    }

    public String guessYear(File mp3) {
        String path = mp3.getPath();
        StringTokenizer st = new StringTokenizer(path, fileSeparator);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            StringTokenizer st2 = new StringTokenizer(token, firstDataSeparator);
            while (st2.hasMoreTokens()) {
                String token2 = st2.nextToken().trim();
                Matcher m = yearPattern.matcher(token2);
                if (m.matches()) {
                    return token2;
                }
            }
        }
        return "";
    }

}