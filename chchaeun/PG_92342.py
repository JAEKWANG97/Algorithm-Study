global diff, answer
diff = 0
answer = [-1]

def backtracking(score, depth, apeach, ryan, n, info, index):
    global diff, answer
    
    if depth == n:
        if ryan <= apeach:
            return
        
        if ryan - apeach == diff:
            for i in range(10, -1, -1):
                if answer[i] < score[i]:
                    answer = score.copy()
                    break
                if answer[i] > score[i]:
                    break
                    
        elif ryan - apeach > diff:
            diff = ryan - apeach
            answer = score.copy()
        
        return
    
    for i in range(index, 11):
        a, r = 0, 0
        
        if info[i] == score[i]:
            if info[i] > 0:
                a = -(10 - i)    
            r = 10 - i
        
        score[i] += 1
        backtracking(score, depth + 1, apeach + a, ryan + r, n, info, i)
        score[i] -= 1
        
def solution(n, info):
    score = [0] * 11
    apeach = 0
    
    for i in range(11):
        if info[i] > 0:
            apeach += 10 - i

    backtracking(score, 0, apeach, 0, n, info, 0)
    
    return answer