/******************************************************************************
 *
 *  The abstract class for Sound.
 *
 ******************************************************************************/

package uet.oop.bomberman.sound;

/**
 * The {@code Sound} class is the data type for sound of all the things in this game.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class Sound {

    public static final MediaPlayer TitleBGM = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/TitleScreen.mp3").toString())
    );
    public static final MediaPlayer stageStartBGM = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/StageStart.mp3").toString())
    );
    public static final MediaPlayer gameOverBGM = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/GameOver.mp3").toString())
    );

    public static final MediaPlayer stageClearBgm = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/StageClear.mp3").toString())
    );

    public static final MediaPlayer MainBGM = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/MainBGM.mp3").toString())
    );

    public static final MediaPlayer explosionSound = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/ExplosionSE.mp3").toString())
    );

    public static final MediaPlayer LayBombSound = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/LayBombSE.wav").toString())
    );

    public static final MediaPlayer powerUpSound = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/PowerUpSE.wav").toString())
    );

    public static final MediaPlayer bomberisKilledSound = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/BomberKilledSE.wav").toString())
    );

    public static final MediaPlayer allEnemiesIsKilledSound = new MediaPlayer(
            new Media(Sound.class.getResource("/audio/KillAllEnemiesSE.wav").toString())
    );

    /**
     * Create a new MediaPlayer with the same media as 'That' (clone).
     * @param that input MediaPlayer.
     * @return the new MediaPlayer clone.
     */
    public static MediaPlayer cloneOf(MediaPlayer that) {
        return new MediaPlayer(that.getMedia());
    }

}


/******************************************************************************
 *  Copyright 2022, Phu Quoc Trung and Tran Thuy Duong.
 *
 *  This file is part of OOP-Bomberman, which accompanies the course
 *
 *      INT2204 of UET-VNU
 *
 *  OOP-Bomberman is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OOP-Bomberman is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  See http://www.gnu.org/licenses.
 ******************************************************************************/