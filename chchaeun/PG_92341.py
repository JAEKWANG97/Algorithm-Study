from collections import defaultdict
import heapq
import math

def solution(fees, records):
    default_time, default_fee, unit_time, unit_fee = fees 
    
    in_record = defaultdict(list)
    total = defaultdict(int)
    
    cars = set()
    
    for record in records:
        time, id, type = record.split()
        cars.add(id)
        hour, minute = map(int, time.split(":"))
        total_minutes = hour * 60 + minute
        
        if type == 'IN':
            in_record[id].append(total_minutes)
        else:
            in_minutes = in_record[id].pop()
            total[id] += total_minutes - in_minutes
    
    for id in in_record.keys():
        if in_record[id]:
            total[id] += 23 * 60 + 59 - in_record[id].pop()
    
    cars = list(cars)
    heapq.heapify(cars)
    
    answer = []
    
    while cars:
        result = default_fee
        car = heapq.heappop(cars)
        
        time = total[car]
        
        if time <= default_time:
            answer.append(result)
            continue
        
        time -= default_time
        
        result += math.ceil(time / unit_time) * unit_fee
        
        answer.append(result)
        
    return answer