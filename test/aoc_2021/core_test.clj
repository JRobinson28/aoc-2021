(ns aoc-2021.core-test
  (:require [clojure.test :refer :all]
            [aoc-2021.core :refer :all]
            [aoc-2021.day-01 :as day-1]
            [aoc-2021.day-02 :as day-2]
            [aoc-2021.day-03 :as day-3]
            [aoc-2021.day-04 :as day-4]))

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
