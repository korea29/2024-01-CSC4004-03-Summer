import React, { useState, useEffect } from "react";
import { ConfigProvider, Space, Popover, Row, Col } from "antd";

import LeftBubble from "./components/LeftBubble";
import RightBubble from "./components/RightBubble";
import HeaderComponent from "./components/HeaderComponent";
import ChatLog from "./components/ChatLog";
import KeywordComponent from "./components/KeywordComponent";

import "../css/ChattingScreen.css";
import defaultAkoImage from "../Images/기본형아코얼굴_(designed by 박세리,원혜림).png";
import listeningAkoImage from "../Images/듣는아코_(designed by 박세리).png";
import phoneAkoImage from "../Images/핸드폰쥔아코_(designed by 박세리).png";
import searchlogo from "../Images/search.png";
import sendButtonImage from "./assets/send.png"; // Replace this with your image file

const ChattingScreen = () => {
  const [inputValue, setInputValue] = useState("");
  const [chatHistory, setChatHistory] = useState([]);
  const [placeholderWidth, setPlaceholderWidth] = useState("auto");

  useEffect(() => {
    // Calculate the width of the placeholder text
    const input = document.getElementById("inputField");
    const placeholderWidth = input.offsetWidth + "px";
    setPlaceholderWidth(placeholderWidth);
  }, [inputValue]);

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
          <ChatLog></ChatLog>
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
            <div className="container">
              <div className="content">
                <div className="inputcontainer">
                  <KeywordComponent />
                  <img src={searchlogo} alt="searchlogo" />
                  <input
                    id="inputField"
                    type="text"
                    placeholder="안녕, 오랜만이야."
                    value={inputValue}
                    onChange={(e) => setInputValue(e.target.value)}
                    style={{ width: placeholderWidth }} // Dynamically adjust width
                  />
                  <img
                    src={sendButtonImage}
                    alt="Send"
                    style={{ cursor: "pointer" }}
                    onClick={() => console.log("Send button clicked")}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChattingScreen;
