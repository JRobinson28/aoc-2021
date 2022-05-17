(ns aoc-2021.core-test
  (:require [clojure.test :refer :all]
            [aoc-2021.core :refer :all]
            [aoc-2021.day-01 :as day-1]
            [aoc-2021.day-02 :as day-2]
            [aoc-2021.day-03 :as day-3]
            [aoc-2021.day-04 :as day-4]
            [aoc-2021.day-05 :as day-5]
            [aoc-2021.day-06 :as day-6]
            [aoc-2021.day-07 :as day-7]
            [aoc-2021.day-08 :as day-8]))

(deftest day-1
  (testing "day 1 sample part 1"
    (is (= 7 (day-1/part-1 "day_01_test"))))
  (testing "day 1 sample part 2"
    (is (= 5 (day-1/part-2 "day_01_test")))))

(deftest day-2
  (testing "day 2 sample part 1"
    (is (= 150 (day-2/part-1 "day_02_test"))))
  (testing "day 2 sample part 2"
    (is (= 900 (day-2/part-2 "day_02_test")))))

(deftest day-3
  (testing "day 3 sample part 1"
    (is (= 198 (day-3/part-1 "day_03_test"))))
  (testing "day 3 sample part 2"
    (is (= 230 (day-3/part-2 "day_03_test")))))

(deftest day-4
  (testing "day 4 sample part 1"
    (is (= 4512 (day-4/part-1 "day_04_test"))))
  (testing "day 4 sample part 2"
    (is (= 1924 (day-4/part-2 "day_04_test")))))

(deftest day-5
  (testing "day 5 sample part 1"
    (is (= 5 (day-5/part-1 "day_05_test"))))
  (testing "day 5 sample part 2"
    (is (= 12 (day-5/part-2 "day_05_test")))))

(deftest day-6
  (testing "day 6 sample part 1"
    (is (= 5934 (day-6/part-1 "day_06_test"))))
  (testing "day 5 sample part 2"
    (is (= 26984457539 (day-6/part-2 "day_06_test")))))

(deftest day-7
  (testing "day 7 sample part 1"
    (is (= 37 (day-7/part-1 "day_07_test"))))
  (testing "day 7 sample part 2"
    (is (= 168 (day-7/part-2 "day_07_test")))))

(deftest day-8
  (testing "day 8 sample part 1"
    (is (= 26 (day-8/part-1 "day_08_test"))))
  (testing "day 7 sample part 2"
    (is (= 61229 (day-8/part-2 "day_08_test")))))
