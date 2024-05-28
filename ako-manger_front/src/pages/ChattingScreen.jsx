import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Button, List } from "antd";
import LeftBubble from "./components/LeftBubble";
import RightBubble from "./components/RightBubble";
import HeaderComponent from "./components/HeaderComponent";
import KeywordComponent from "./components/KeywordComponent";
import "../css/ChattingScreen.css";
import searchlogo from "../Images/search.png";
import sendButtonImage from "./assets/send.png";
import { TbBubblePlus } from "react-icons/tb";
import axios from "axios";

const ChattingScreen = () => {
  const location = useLocation();
  const initialMessage = location.state?.message || "";
  const [inputValue, setInputValue] = useState("");
  const [chatHistory, setChatHistory] = useState([]);
  const [chatSessions, setChatSessions] = useState([]);
  const [selectedSessionIndex, setSelectedSessionIndex] = useState(null);

  useEffect(() => {
    if (initialMessage) {
      addNewChat(initialMessage);
    }
  }, [initialMessage]);

  const fetchData = async () => {
    try {
      // 로그인 요청
      const response = await axios.post("http://localhost:8080/login", {
        studentId: "1122", // 사용자가 입력한 학번
        password: "1122", // 사용자가 입력한 비밀번호
      });

      // 응답 처리
      console.log("로그인 완료:", response.data);
    } catch (error) {
      // 오류 처리
      console.error("로그인 오류:", error);
    }
  };

  const addNewChat = async (chat) => {
    const updatedChatHistory = [...chatHistory, { user: chat }];
    setChatHistory(updatedChatHistory);

    setInputValue("");

    try {
      const response = await axios.post("http://localhost:8080/chat/ask", {
        Username: "User",
        UserInput: chat,
      });

      const reply = response.data;

      setChatHistory((prevChatHistory) => {
        const newChatHistory = [...prevChatHistory, { bot: reply }];
        return newChatHistory;
      });
    } catch (error) {
      console.error("Error fetching chat reply:", error);
      if (error.response && error.response.status === 403) {
        alert("접근 권한이 없습니다. 다시 시도해 주세요.");
      } else {
        alert("서버와의 통신 중 오류가 발생했습니다.");
      }
    }
  };

  const startNewChat = () => {
    if (chatHistory.length > 0) {
      setChatSessions([...chatSessions, chatHistory]);
      setChatHistory([]);
    }
  };

  const selectChatSession = (index) => {
    setSelectedSessionIndex(index);
    setChatHistory(chatSessions[index]);
  };

  return (
    <div className="wrap">
      <HeaderComponent />
      <div className="chatting-container">
        <div className="chatting-log">
          <List
            header={
              <div
                style={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <Button onClick={startNewChat} style={{ margin: "20px 0" }}>
                  New Chat <TbBubblePlus />
                </Button>
              </div>
            }
            bordered
            dataSource={chatSessions.map((_, index) => `Session ${index + 1}`)}
            renderItem={(item, index) => (
              <List.Item
                onClick={() => selectChatSession(index)}
                style={{ cursor: "pointer" }}
              >
                {chatSessions[index][0]?.user || item}
              </List.Item>
            )}
          />
        </div>
        <div className="chatting-screen">
          <div className="chatwrapper">
            <div className="chat">
              {chatHistory.map((chat, index) =>
                chat.user ? (
                  <RightBubble key={index} message={chat.user} />
                ) : (
                  <LeftBubble key={index} message={chat.bot} />
                )
              )}
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
                  />
                  <img
                    src={sendButtonImage}
                    alt="Send"
                    style={{ cursor: "pointer" }}
                    onClick={() => {
                      if (inputValue.trim()) {
                        addNewChat(inputValue);
                      }
                    }}
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
