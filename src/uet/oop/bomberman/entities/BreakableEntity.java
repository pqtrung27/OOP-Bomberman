/******************************************************************************
 *
 *  Dependency: Entity.java
 *
 *  The abstract data type for the Breakable Entity in general.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities;

/**
 * The {@code Enemy} class is the data type for all the Breakable Entity
 * in this game in general.
 *
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Lớp trừu tượng cài đặt các đối tượng có thể phá hủy.
 *
 * @author TTD
 */
public abstract class BreakableEntity extends Entity {
    // Biến boolean kiểm tra trạng thái của đối tượng
    public boolean isBroken; // true = đã bị phá hủy
    protected boolean isExploding; // true = đang nổ
    protected int animate = 0;
    protected final int animate_MAX_VALUE = 7500;

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha Entity.
     * Gán trạng thái ban đầu chưa bị phá hủy, không đang nổ cho đối tượng.
     * Khởi tạo đối tượng nền Grass.
     */
    public BreakableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isBroken = false;
        isExploding = false;
    }

    /**
     * Phương thức kiểm tra trạng thái của đối tượng.
     * @return trạng thái hiện tại của đối tượng, true = đã bị phá hủy, false = chưa bị phá hủy
     */
    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public boolean isExploding() {
        return isExploding;
    }

    /**
     * Phương thức chuyển trạng thái của đối tượng.
     * Gán trạng thái đang nổ cho đối tượng.
     * Cài đặt timer, gán trạng thái đã bị phá hủy cho đối tượng sau 750ms.
     */
    public void breakEntity() {
        isExploding = true;
        animate = 0;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isExploding = false;
                isBroken = true;
            }
        }, 500L);
    }

    /**
     * Ghi đè phương thức render() của lớp cha Entity.
     * Chỉ cho phép render khi đối tượng có trạng thái chưa bị phá hủy.
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        if (!isBroken) {
            super.render(gc);
        }
    }

    protected void animate() {
        if (animate < animate_MAX_VALUE) {
            animate++;
        } else animate = 0;
    }

    @Override
    public void update() {

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