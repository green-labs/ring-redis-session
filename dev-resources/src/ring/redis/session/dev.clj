(ns ring.redis.session.dev
  (:require [celtuce.codec :as codec]
            [celtuce.connector :as conn]
            [ring.redis.session :refer [redis-store]]))

(defn make-session-store
  "Provide some sane default for local dev."
  ([]
   (make-session-store
    (conn/redis-server
     "redis://localhost:6379"
     :codec (codec/utf8-string-codec)
     :conn-options {:timeout 5000})))
  ([conn]
   (make-session-store
    conn
    {:expire-secs 43200
     :reset-on-read true}))
  ([conn opts]
   (redis-store conn opts)))

(def default-connection (make-session-store))
