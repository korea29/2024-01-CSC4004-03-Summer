import sys                      # 자바 flush 함수를 이용하여 데이터를 받기 위한 라이브러리
import logging                  # 로그 생성을 위한 라이브러리

username = sys.stdin.readline().strip()             # 자바에서 flush 받아서 이름 사용


def calculate_match_score(rule_words, request):
    match_score = -1
    for word in rule_words:
        if word in request:
            match_score += 1
    return match_score


def chat(request):
    best_match_score = -1
    best_response = "무슨 말인지 이해하지 못했어요. 다른 단어를 사용해서 질문해 주세요!"

    for idx, rule_words in enumerate(chat_rules):
        current_score = calculate_match_score(rule_words, request)
        if current_score > best_match_score:
            best_match_score = current_score
            best_response = chat_response[idx]
    return best_response

chat_rules = [['안녕', '반가워'],
              ['이름', '뭐야'],
              ['소개'],
              ['졸업', '남은', '학점'],
              ['졸업', '기준', '어떻게'],
              ['이수', '체계도'],
              ['지금까지', '내역', '수강한'],
              ['추천', '시간표'], ['고마워', '굿', '땡큐', '감사'], ['선이수', '먼저', '그 전']]

chat_response = ['안녕하세요! 아코 매니저입니다~ 원하시는 질문이 있으시다면 물어봐 주세요!',
                 '제 이름은 아코 매니저입니다!',
                 '안녕하세요~ 저는 동국대학교 학생들을 돕기 위해 이 세상에 태어난 아코 매니저입니다!',
                 username + '님이 수강해야 하는 과목들에 대한 정보를 불러왔어요',
                 username + '님이 다니시는 전공의 이수체계도를 가지고 왔어요',
                 '다니시는 전공의 이수체계도를 가지고 왔어요',
                 '쉽게 보실 수 있도록 원그래프 형식으로 들고 와 봤어요',
                 '수강해야 하는 과목들을 기준으로 추천 시간표를 짜 봤어요',
                 '해결되었다니 정말 다행이네요! 언제든 도움이 필요하시다면 아코 매니저를 불러 주세요!',
                 '해당 과목을 듣기 위해서 추가로 수강해야 하는 과목들을 가지고 왔어요']

req = sys.stdin.readline().strip()

print(chat(req))
