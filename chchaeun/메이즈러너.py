class Exit:
    def __init__(self, y, x):
        self.y = y
        self.x = x

    def get_distance(self, y, x):
        return abs(self.y - y) + abs(self.x - x)

def in_range(y, x):
    return 0 <= y < N and 0 <= x < N

def move():
    global total_amount, board, player
    dys, dxs = [1, -1, 0, 0], [0, 0, 1, -1]
    
    nplayer = [[0 for _ in range(N)] for _ in range(N)]

    for i in range(N):
        for j in range(N):
            if player[i][j] == 0:
                continue

            min_distance = exit.get_distance(i, j)
            min_point = (i, j)

            for dy, dx in zip(dys, dxs):
                ny, nx = i + dy, j + dx
                new_distance = exit.get_distance(ny, nx)

                if min_distance > new_distance and not 0 < board[ny][nx] < EXIT:
                    min_distance = new_distance
                    min_point = (ny, nx)

            my, mx = min_point
            # print(i, j, min_point)

            if (i, j) != min_point:
                total_amount += player[i][j]

            if board[my][mx] != EXIT:
                nplayer[my][mx] += player[i][j]

    player = nplayer

def find():
    for size in range(2, N + 1):
        for i in range(N):
            for j in range(N):
                if check(i, j, size):
                    return (i, j, size)

def check(y, x, size):
    player_flag = False
    exit_flag = False
    for i in range(y, y + size):
        for j in range(x, x + size):
            if not in_range(i, j):
                return False

            if player[i][j] > 0:
                player_flag = True
            
            elif board[i][j] == EXIT:
                exit_flag = True

    return player_flag and exit_flag 

def rotation(y, x, size):
    global board, player
    nboard = [[board[i][j] for j in range(N)] for i in range(N)]
    nplayer = [[player[i][j] for j in range(N)] for i in range(N)]

    for i in range(size):
        for j in range(size):
            nboard[y + i][x + j] = board[y + size - 1 - j][x + i]
            nplayer[y + i][x + j] = player[y + size - 1 - j][x + i]
            
            if 0 < nboard[y + i][x + j] < EXIT:
                nboard[y + i][x + j] -= 1
            elif nboard[y + i][x + j] == EXIT:
                exit.y, exit.x = y + i, x + j

    board = nboard
    player = nplayer

EXIT = 10
N, M, K = map(int, input().split())

global board, total_amount, player
total_amount = 0
board = [list(map(int, input().split())) for _ in range(N)]
player = [[0 for _ in range(N)] for _ in range(N)]

for i in range(M):
    y, x = map(int, input().split())
    player[y - 1][x - 1] += 1

y, x = map(int, input().split())
exit = Exit(y - 1, x - 1)
exit.y, exit.x = y - 1, x - 1
board[y - 1][x - 1] = EXIT

for _ in range(1, K+1):
    # print(_, "탄")
    move()
    player_count = 0

    for i in range(N):
        for j in range(N):
            if player[i][j] > 0:
                player_count += 1

    # print("이동횟수:", total_amount)

    if player_count == 0:
        break
    y, x, size = find()
    # print(y, x, size)
    rotation(y, x, size)

    # for i in range(N):
    #     for j in range(N):
    #         print(board[i][j], end=" ")
    #     print()
    # print()
    
    # for i in range(N):
    #     for j in range(N):
    #         print(player[i][j], end=" ")
    #     print()
    # print()

print(total_amount)
print(exit.y + 1, exit.x + 1)