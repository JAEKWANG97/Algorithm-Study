import heapq

class Bus:
    def __init__(self, start, seat):
        self.start = start
        self.seat = seat
        self.passengers = []
    def add(self, time):
        self.passengers.append(time)

    
def getMinutes(time):
    hours, minutes = time.split(":")
    return int(hours) * 60 + int(minutes)

def solution(n, t, m, timetable):
    hq = []
    
    for time in timetable:
        heapq.heappush(hq, getMinutes(time))

    start = getMinutes("9:00")
    buses = []
    for i in range(n):
        buses.append(Bus(start, m))
        start += t
        
    current = 0
    answer = 0
    
    while hq:
        bus = buses[current]
        first = heapq.heappop(hq)
        
        if bus.start >= first:
            if bus.seat == 1 and len(buses) - 1 == current:
                answer = first - 1
                bus.seat -= 1
                break
            elif bus.seat > 0:
                bus.seat -= 1
                bus.add(first)
            else:
                if len(buses) - 1 > current:
                    current += 1
                    heapq.heappush(hq, first)
                else:
                    answer = first - 1
                    bus.seat -= 1
                    break
        else:
            if len(buses) - 1 > current:
                current += 1
                heapq.heappush(hq, first)
                continue
            
            prev_bus = buses[current - 1]
            
            if prev_bus.seat > 0:
                answer = prev_bus.start
                prev_bus.seat -= 1
            else:
                answer = prev_bus.passengers[-1] - 1
                
            break
    
    for bus in buses:
        if bus.seat > 0:
            answer = max(answer, bus.start)
    
    return f"{str(answer // 60).zfill(2)}:{str(answer % 60).zfill(2)}"
            
                    