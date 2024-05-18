import React from "react";
import { PieChart } from "react-minimal-pie-chart";

// 총 학점에 비례해서 차트의 크기를 다르게 설정하는 함수

const calculateChartSize = (totalValue) => {
  const minSize = 70;
  const maxSize = 400;

  const size = Math.max(minSize, Math.min(maxSize, totalValue));

  return size * 2.2 + "px";
};

const PieChartComponent = ({ data }) => {
  const chartSize = calculateChartSize(data.totalValue); // Calculate chart size based on totalValue

  const chartStyles = {
    background: "rgba(228, 195, 162, 0.4)",
    borderRadius: "50%",
    border: "none",
    display: "block",
    margin: "auto 0",
    width: chartSize,
    height: chartSize,
  };

  return (
    <PieChart
      data={[data]}
      reveal={(data.value / data.totalValue) * 100}
      lineWidth={35}
      rounded
      animate
      label={(labelRenderProps) => labelRenderProps.dataEntry.value + "학점"}
      style={chartStyles}
    />
  );
};

export default PieChartComponent;
