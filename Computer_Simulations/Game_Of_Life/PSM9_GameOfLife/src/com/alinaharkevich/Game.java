package com.alinaharkevich;

public class Game {

    int width;
    int height;
    int[][] grid;
    private final int xMax;
    private final int yMax;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[width][height];
        xMax = width-1;
        yMax = height-1;
    }

    public void printGrid() {
        System.out.println(" ---------- ");
        for (int i = 0; i < height; i++) {
            String line = "|";
            for (int j = 0; j < width; j++) {
                if (this.grid[j][i] == 0) {
                    line += "."; // dead = 0
                } else {
                    line += "*"; // alive = 1
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println(" ---------- \n");
    }

    public void setLive(int x, int y) {
        this.grid[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.grid[x][y] = 0;
    }

    public int boardSituation(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }

        if (y < 0 || y >= height) {
            return 0;
        }

        return this.grid[x][y];
    }

    public int culLiveNeighbours(int x, int y) {
        int count = 0;
        Cell c1 = new Cell(x - 1, y - 1);
        Cell c2 = new Cell(x, y - 1);
        Cell c3 = new Cell(x + 1, y - 1);
        Cell c4 = new Cell(x + 1, y);
        Cell c5 = new Cell(x + 1, y + 1);
        Cell c6 = new Cell(x, y + 1);
        Cell c7 = new Cell(x - 1, y + 1);
        Cell c8 = new Cell(x - 1, y);

        //TORUS,CICLED GRID
        //in order to see cycled condition if a grid, please make 12 and more iterations, because we have a big grid
        if (x == 0) {
            if (y == 0) {
                c1.setX(xMax);
                c1.setY(yMax);
                c2.setX(0);
                c2.setY(yMax);
                c3.setX(1);
                c3.setY(yMax);
                c7.setX(xMax);
                c7.setY(1);
                c8.setX(xMax);
                c8.setY(0);
            }else if (y == yMax) {
                c1.setX(xMax);
                c1.setY(yMax-1);
                c5.setX(1);
                c5.setY(0);
                c6.setX(0);
                c6.setY(0);
                c7.setX(xMax);
                c7.setY(0);
                c8.setX(xMax);
                c8.setY(yMax);
            }else {
                c1.setX(xMax);
                c1.setY(y-1);
                c8.setX(xMax);
                c8.setY(y);
                c7.setX(xMax);
                c7.setY(y+1);
            }
        }else if (x == xMax) {
            if (y == 0) {
                c1.setX(xMax-1);
                c1.setY(yMax);
                c2.setX(xMax);
                c2.setY(yMax);
                c3.setX(0);
                c3.setY(yMax);
                c4.setX(0);
                c4.setY(0);
                c5.setX(0);
                c5.setY(1);
            }else if (y == yMax) {
                c3.setX(0);
                c3.setY(yMax-1);
                c4.setX(0);
                c4.setY(yMax);
                c5.setX(0);
                c5.setY(0);
                c6.setX(xMax);
                c6.setY(0);
                c7.setX(xMax-1);
                c7.setY(0);
            }else {
                c3.setX(0);
                c3.setY(y-1);
                c4.setX(0);
                c4.setY(y);
                c5.setX(0);
                c5.setY(y+1);
            }
        }else if (y == 0) {
            c1.setX(x-1);
            c1.setY(yMax);
            c2.setX(x);
            c2.setY(yMax);
            c3.setX(x+1);
            c3.setY(yMax);
        } else if (y == height - 1) {
            c5.setX(x+1);
            c5.setY(0);
            c6.setX(x);
            c6.setY(0);
            c7.setX(x-1);
            c7.setY(0);
        }
        Cell[] neighbours = {c1, c2, c3, c4, c5, c6, c7, c8};
        for (Cell neighbour : neighbours) {
            count += grid[neighbour.x][neighbour.y];
        }
        return count;
    }

    public void step(int a, int b) {
        int[][] newGrid = new int[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int liveNeighbours = culLiveNeighbours(j, i);
                if (boardSituation(j, i) == 1) {
                    if (liveNeighbours < 2) {
                        newGrid[j][i] = 0;
                    } else if (liveNeighbours == a || liveNeighbours == b) {
                        newGrid[j][i] = 1;
                    } else if (liveNeighbours > 3) {
                        newGrid[j][i] = 0;
                    }
                } else {
                    if (liveNeighbours == 3) {
                        newGrid[j][i] = 1;
                    }
                }

            }
        }
        this.grid = newGrid;
    }
    class Cell {
        int x;
        int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
}

