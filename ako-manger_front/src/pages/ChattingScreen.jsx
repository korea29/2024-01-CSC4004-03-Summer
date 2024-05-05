import React, { useState } from "react";
import { Button, ConfigProvider, Space, Popover, Row, Col } from "antd";

import LeftBubble from "./components/LeftBubble";
import RightBubble from "./components/RightBubble";
import HeaderComponent from "./components/HeaderComponent";
import ChatLog from "./components/ChatLog";

import "../css/ChattingScreen.css";
import defaultAkoImage from "../Images/기본형아코얼굴_(designed by 박세리,원혜림).png";
import listeningAkoImage from "../Images/듣는아코_(designed by 박세리).png";
import phoneAkoImage from "../Images/핸드폰쥔아코_(designed by 박세리).png";
import searchlogo from "../Images/search.png";

const ChattingScreen = () => {
  const [inputValue, setInputValue] = useState("");
  const [chatHistory, setChatHistory] = useState([]);

  // 최대 보여질 채팅 수
  const maxChatCount = 10;

  // 새로운 채팅 추가 함수
  const addNewChat = (chat) => {
    setChatHistory((prevChatHistory) => {
      const newChatHistory = [...prevChatHistory, chat];
      // 채팅이 일정 수준 이상이면 최신 채팅만 남기기
      if (newChatHistory.length > maxChatCount) {
        return newChatHistory.slice(-maxChatCount);
      }
      return newChatHistory;
    });
  };

  return (
    <div className="wrap">
      <HeaderComponent />
      <div className="chatting-container">
        <div className="chatting-log">
          <ChatLog></ChatLog>
        </div>
        <div className="chatting-screen">
          <div className="chatwrapper">
            {/* <div className="chat">
            {chatHistory.map((chat, index) => (
              <div key={index}>{chat}</div>
            ))}
          </div> */}
            <div className="chat">
              <LeftBubble />
              <RightBubble />
              <LeftBubble />
              <RightBubble />
              <LeftBubble />
              <RightBubble />
              <LeftBubble />
              <RightBubble />
              <LeftBubble />
              <RightBubble />
              <LeftBubble />
              <RightBubble />
            </div>
            <div className="inputcontainer">
              <img src={searchlogo} alt="searchlogo" />
              <input
                type="text"
                placeholder="Search"
                value={inputValue}
                onChange={(e) => setInputValue(e.target.value)}
              />
              <button>Send</button>
              <ConfigProvider
                theme={{
                  token: {
                    // 전공 버튼 컬러
                    colorPrimary: "#867060",
                    borderRadius: 10,
                    paddingInline: "auto",
                    // 교양 버튼 컬러
                    colorBgContainer: "#B3C278",
                  },
                }}
              >
                <Popover
                  content={
                    <div>
                      <p>왜 현재 1위 인가</p>
                    </div>
                  }
                  title="몇 위 인지"
                  trigger="click"
                  color="#F2E2DE"
                  arrow={{ pointAtCenter: true }}
                >
                  <Button
                    defaultHoverColor="#000" // 호버 시 텍스트 색상을 검은색(#000)으로 변경
                    paddingInline={20} // 가로 안쪽 여백을 20px로 변경
                    paddingBlock={10} // 세로 안쪽 여백을 10px로 변경
                    fontWeight={500} // 폰트 굵기를 500으로 변경
                  >
                    1위 키워드
                  </Button>
                </Popover>
              </ConfigProvider>
            </div>
            <div className="keywordcontainer">
              {/* 샵 키워드 컴포넌트로 분리*/}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChattingScreen;
