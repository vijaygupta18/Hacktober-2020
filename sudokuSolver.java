class Solution {
    private static final char EMPTY = '.';
    public void solveSudoku(char[][] board) {
        canSolve(board, 0, 0);
    }
    public boolean canSolve(char [][] board, int row, int col) {
        if (col == board[row].length) {
            col = 0;
            row += 1;
            if (row == board.length)
                return true;
        }
        if (board[row][col] != EMPTY)            // Already Filled
            return canSolve(board, row, col+1);
        for (int value=1; value<=board.length; value++) {
            char charToPlace = (char) (value + '0');
            if (canPlace(board, row, col, charToPlace)) {
                board[row][col] = charToPlace;
                if (canSolve(board, row, col+1))
                    return true;
                board[row][col] = EMPTY;
            }
        }
        return false;
    }
    public boolean canPlace(char [][] board, int row, int col, char charToPlace) {
        // check the column
        for (char [] placementRow: board) {
            if (charToPlace == placementRow[col])
                return false;
        }
        // check the row
        for (int i=0; i<board[row].length; i++) {
            if (charToPlace == board[row][i])
                return false;
        }
        // compute the size of the subboxes
        int regionSize = (int) Math.sqrt(board.length);
        int vertical = row/regionSize;
        int horizontal = col/regionSize;
        int topLeftSubRow = regionSize * vertical;
        int topLeftSubCol = regionSize * horizontal;
        
        for (int i=0; i<regionSize; i++) {
            for (int j=0; j<regionSize; j++) {
                if (charToPlace == board[topLeftSubRow+i][topLeftSubCol+j])
                    return false;
            }
        }
        return true;
    }
}
