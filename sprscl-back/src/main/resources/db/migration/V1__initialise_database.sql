CREATE TABLE good (
  id      UUID PRIMARY KEY,
  name    VARCHAR(255),
  price   DECIMAL(19, 4),
  version INT NOT NULL DEFAULT 0
);

CREATE TABLE agent (
  id      UUID PRIMARY KEY,
  name    VARCHAR(255),
  version INT NOT NULL DEFAULT 0
);

CREATE TABLE organization (
  id      UUID PRIMARY KEY,
  name    VARCHAR(255),
  version INT NOT NULL DEFAULT 0
);

CREATE TABLE operation (
  id              UUID PRIMARY KEY,
  dtype           VARCHAR(31),
  name            VARCHAR(255),
  organization_id UUID NOT NULL REFERENCES organization (id),
  agent_id        UUID NOT NULL REFERENCES agent (id),
  version         INT  NOT NULL DEFAULT 0
);
CREATE INDEX i__operation__organization_id ON operation USING BTREE (organization_id);
CREATE INDEX i__operation__agent_id ON operation USING BTREE (agent_id);

CREATE TABLE motion (
  id           UUID PRIMARY KEY,
  quantity     INT,
  price        DECIMAL(19, 4),
  good_id      UUID NOT NULL REFERENCES good (id),
  operation_id UUID NOT NULL REFERENCES operation (id),
  version      INT  NOT NULL DEFAULT 0
);
CREATE INDEX i__motion__operation_id ON motion USING BTREE (operation_id);
CREATE INDEX i__motion__good_id ON motion USING BTREE (good_id);