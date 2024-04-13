from collections import defaultdict, deque
import heapq

class Tower:
    def __init__(self, power, y, x):
        self.power = power
        self.y = y
        self.x = x
        self.last_attack = 0

    def __str__(self):
        return f'공격력: {self.power} / 좌표: {self.y}, {self.x}'

def get_attacker_target():
    hq = []

    for i in range(N):
        for j in range(M): 
            tower = towers[i][j]
            if tower.power <= BROKEN:
                continue
            heapq.heappush(hq, (tower.power, -tower.last_attack, -(tower.y+tower.x), -tower.x, tower))
    
    attacker = heapq.heappop(hq)[-1]

    if not hq:
        return [attacker, None]

    target = heapq.nlargest(1, hq)[0][-1]

    return [attacker, target]


def distance(dys, dxs, dist, sy, sx):
    dq = deque([(sy, sx)])

    dist[sy][sx] = 0

    while dq:
        y, x = dq.popleft()

        for dy, dx in zip(dys, dxs):
            ny, nx = (y + dy) % N, (x + dx) % M

            if towers[ny][nx].power <= BROKEN:
                continue

            if dist[ny][nx] > dist[y][x] + 1:
                dist[ny][nx] = dist[y][x] + 1
                dq.append((ny, nx))

def laser():
    dys = [0, 1, 0, -1]
    dxs = [1, 0, -1, 0]
    dist = [[INF for _ in range(M)] for _ in range(N)]

    distance(dys, dxs, dist, target.y, target.x)

    if dist[attacker.y][attacker.x] == INF:
        return False

    y, x = attacker.y, attacker.x

    while (y, x) != (target.y, target.x):
        for dy, dx in zip(dys, dxs):
            ny, nx = (y + dy) % N, (x + dx) % M

            if dist[y][x] - 1 == dist[ny][nx]:
                y, x = ny, nx
                related[(y, x)] = True

                if (y, x) == (target.y, target.x):
                    towers[y][x].power -= attacker.power
                else:
                    towers[y][x].power -= attacker.power // 2
                
                break
    
    return True

def bomb():
    dys = [-1, -1, -1, 0, 0, 1, 1, 1]
    dxs = [-1, 0, 1, -1, 1, -1, 0, 1]

    for dy, dx in zip(dys, dxs):
        ny, nx = (target.y + dy) % N, (target.x + dx) % M

        if (ny, nx) == (attacker.y, attacker.x) or towers[ny][nx].power <= BROKEN:
            continue
        
        towers[ny][nx].power -= attacker.power // 2
        related[(ny, nx)] = True

    target.power -= attacker.power
    related[(target.y, target.x)] = True

def fix():
    for i in range(N):
        for j in range(M):
            if towers[i][j].power <= BROKEN:
                continue

            if not related[(i, j)]:
                towers[i][j].power += 1

INF = int(1e9)
BROKEN = 0
N, M, K = map(int, input().split())
towers = [[Tower(power, i, j) for j, power in enumerate(list(map(int, input().split())))] for i in range(N)]

answer = 0
for k in range(K):
    attacker, target = get_attacker_target()
    if not target:
        break

    attacker.power += N + M
    attacker.last_attack = k + 1

    related = defaultdict(bool)
    related[(attacker.y, attacker.x)] = True

    if not laser():
        bomb()

    fix()
    if k == 999:
        for i in range(N):
            for j in range(M):
                print(towers[i][j].power, end=" ")
            print()
        print()
for i in range(N):
    for j in range(M):
        answer = max(towers[i][j].power, answer)
    #     print(towers[i][j].power, end=" ")
    # print()

print(answer)