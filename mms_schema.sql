--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: achat; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE achat (
    numeroachat integer NOT NULL,
    idetat integer NOT NULL,
    idfournisseur integer NOT NULL,
    idsession integer NOT NULL,
    montanttotalachatht numeric,
    montanttotalachatttc numeric,
    dateachat date
);


--ALTER TABLE public.achat OWNER TO zerbo;

--
-- Name: categorie; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE categorie (
    designation character varying(254) NOT NULL,
    quantitestock integer,
    prixunitairevente numeric,
    prixunitaireachat numeric,
    idcategorie integer NOT NULL,
    quantiteideale integer,
    quantitesecurite integer
);


--ALTER TABLE public.categorie OWNER TO zerbo;

--
-- Name: categorie_idcategorie_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE categorie_idcategorie_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.categorie_idcategorie_seq OWNER TO zerbo;

--
-- Name: categorie_idcategorie_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zerbo
--

ALTER SEQUENCE categorie_idcategorie_seq OWNED BY categorie.idcategorie;


--
-- Name: client_idclient_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE client_idclient_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.client_idclient_seq OWNER TO zerbo;

--
-- Name: client; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE client (
    idclient integer DEFAULT nextval('client_idclient_seq'::regclass) NOT NULL,
    nomclient character varying(254) NOT NULL,
    telephoneclient character varying(254),
    prenomclient character varying(254) NOT NULL
);


--ALTER TABLE public.client OWNER TO zerbo;

--
-- Name: creditclient; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE creditclient (
    idcreditclient integer NOT NULL,
    idsession integer NOT NULL,
    idclient integer NOT NULL,
    idetat integer NOT NULL,
    montanttotalht numeric,
    montanttotalttc numeric,
    datecredit date,
    montantpaye numeric
);


--ALTER TABLE public.creditclient OWNER TO zerbo;

--
-- Name: creditfournisseur; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE creditfournisseur (
    idcreditfournisseur integer NOT NULL,
    idetat integer NOT NULL,
    idfournisseur integer NOT NULL,
    idsession integer NOT NULL,
    montanttotalht numeric,
    montanttotalttc numeric,
    datecredit date,
    montantpaye numeric
);


--ALTER TABLE public.creditfournisseur OWNER TO zerbo;

--
-- Name: depense; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE depense (
    iddepense integer NOT NULL,
    idsession integer NOT NULL,
    libelledepense character varying(254),
    montantdepense numeric
);


--ALTER TABLE public.depense OWNER TO zerbo;

--
-- Name: etat; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE etat (
    idetat integer NOT NULL,
    libelleetat character varying(254)
);


--ALTER TABLE public.etat OWNER TO zerbo;

--
-- Name: fournisseur_idfournisseur_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE fournisseur_idfournisseur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.fournisseur_idfournisseur_seq OWNER TO zerbo;

--
-- Name: fournisseur; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE fournisseur (
    idfournisseur integer DEFAULT nextval('fournisseur_idfournisseur_seq'::regclass) NOT NULL,
    nomfournisseur character varying(254) NOT NULL,
    telephonefournisseur character varying(254),
    prenomfournisseur character varying(254) NOT NULL
);


--ALTER TABLE public.fournisseur OWNER TO zerbo;

--
-- Name: lignecreditachat; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE lignecreditachat (
    idcreditfournisseur integer NOT NULL,
    idligneachat integer NOT NULL,
    idcategorie integer NOT NULL,
    quantiteligneachat integer,
    montantligneachatht numeric,
    montantligneachatttc numeric
);


--ALTER TABLE public.lignecreditachat OWNER TO zerbo;

--
-- Name: lignecreditvente; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE lignecreditvente (
    idcreditclient integer NOT NULL,
    idlignecredit integer NOT NULL,
    idcategorie integer NOT NULL,
    quantitelignecredit integer,
    montantlignecreditht numeric,
    montantlignecreditttc numeric
);


--ALTER TABLE public.lignecreditvente OWNER TO zerbo;

--
-- Name: lignedachat; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE lignedachat (
    numeroachat integer NOT NULL,
    idligneachat integer NOT NULL,
    idcategorie integer NOT NULL,
    quantiteligneachat integer,
    montantligneachatht numeric,
    montantligneventettc numeric
);


--ALTER TABLE public.lignedachat OWNER TO zerbo;

--
-- Name: lignedevente_idlignevente_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE lignedevente_idlignevente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.lignedevente_idlignevente_seq OWNER TO zerbo;

--
-- Name: lignedevente; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE lignedevente (
    numerovente integer NOT NULL,
    idlignevente integer DEFAULT nextval('lignedevente_idlignevente_seq'::regclass) NOT NULL,
    idcategorie integer NOT NULL,
    quantitelignevente integer,
    montantligneventeht numeric,
    montantligneventettc numeric,
    montantligneventetva numeric
);


--ALTER TABLE public.lignedevente OWNER TO zerbo;

--
-- Name: motdepasse; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE motdepasse (
    idutilisateur integer NOT NULL,
    valeurmdp character varying(254),
    datecreationmdp date,
    datevaliditemdp date,
    idmdp integer NOT NULL
);


--ALTER TABLE public.motdepasse OWNER TO zerbo;

--
-- Name: motdepasse_idmdp_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE motdepasse_idmdp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.motdepasse_idmdp_seq OWNER TO zerbo;

--
-- Name: motdepasse_idmdp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zerbo
--

ALTER SEQUENCE motdepasse_idmdp_seq OWNED BY motdepasse.idmdp;


--
-- Name: parameters; Type: TABLE; Schema: public; Owner: zerbo; Tablespace:
--

CREATE TABLE parameters (
    dureeviemdp integer NOT NULL,
    longueurmdp integer,
    tentativemdp integer,
    tempsinactivitesmdp integer,
    tauxtva double precision
);


--ALTER TABLE public.parameters OWNER TO zerbo;

--
-- Name: payementcreditclient; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE payementcreditclient (
    idcreditclient integer NOT NULL,
    idsession integer NOT NULL,
    datepayement date,
    montant numeric
);


--ALTER TABLE public.payementcreditclient OWNER TO zerbo;

--
-- Name: payementcreditfournisseur; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE payementcreditfournisseur (
    idcreditfournisseur integer NOT NULL,
    idsession integer NOT NULL,
    datepayement date,
    montant numeric
);


--ALTER TABLE public.payementcreditfournisseur OWNER TO zerbo;

--
-- Name: session; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE session (
    idutilisateur integer NOT NULL,
    dureesession integer,
    datedebutsession character varying(60) NOT NULL,
    datefinsession character varying(60),
    idsession integer NOT NULL
);


--ALTER TABLE public.session OWNER TO zerbo;

--
-- Name: session_idsession_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE session_idsession_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.session_idsession_seq OWNER TO zerbo;

--
-- Name: session_idsession_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zerbo
--

ALTER SEQUENCE session_idsession_seq OWNED BY session.idsession;


--
-- Name: typeutilisateur; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE typeutilisateur (
    libelletypeutilisateur character varying(254),
    idtypeutilisateur integer NOT NULL
);


--ALTER TABLE public.typeutilisateur OWNER TO zerbo;

--
-- Name: typeutilisateur_idtypeutilisateur_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE typeutilisateur_idtypeutilisateur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.typeutilisateur_idtypeutilisateur_seq OWNER TO zerbo;

--
-- Name: typeutilisateur_idtypeutilisateur_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zerbo
--

ALTER SEQUENCE typeutilisateur_idtypeutilisateur_seq OWNED BY typeutilisateur.idtypeutilisateur;


--
-- Name: user; Type: TABLE; Schema: public; Owner: zerbo; Tablespace:
--

CREATE TABLE user (
    idtypeutilisateur integer NOT NULL,
    nomutilisateur character varying(254),
    prenomutilisateur character varying(254),
    loginutilisateur character varying(254),
    telephoneutilisateur character varying(254),
    dateembaucheutilisateur date,
    datedepartutilisateur date,
    idutilisateur integer NOT NULL
);


--ALTER TABLE public.user OWNER TO zerbo;

--
-- Name: utilisateur_idutilisateur_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE utilisateur_idutilisateur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.utilisateur_idutilisateur_seq OWNER TO zerbo;

--
-- Name: utilisateur_idutilisateur_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zerbo
--

ALTER SEQUENCE utilisateur_idutilisateur_seq OWNED BY user.idutilisateur;


--
-- Name: vente_numerovente_seq; Type: SEQUENCE; Schema: public; Owner: zerbo
--

CREATE SEQUENCE vente_numerovente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.vente_numerovente_seq OWNER TO zerbo;

--
-- Name: vente; Type: TABLE; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE TABLE vente (
    numerovente integer DEFAULT nextval('vente_numerovente_seq'::regclass) NOT NULL,
    idsession integer NOT NULL,
    idetat integer NOT NULL,
    idclient integer NOT NULL,
    montanttotalventeht numeric,
    montanttotalventettc numeric,
    montanttotalventetva numeric,
    datevente date
);


--ALTER TABLE public.vente OWNER TO zerbo;

--
-- Name: idcategorie; Type: DEFAULT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY categorie ALTER COLUMN idcategorie SET DEFAULT nextval('categorie_idcategorie_seq'::regclass);


--
-- Name: idmdp; Type: DEFAULT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY motdepasse ALTER COLUMN idmdp SET DEFAULT nextval('motdepasse_idmdp_seq'::regclass);


--
-- Name: idsession; Type: DEFAULT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY session ALTER COLUMN idsession SET DEFAULT nextval('session_idsession_seq'::regclass);


--
-- Name: idtypeutilisateur; Type: DEFAULT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY typeutilisateur ALTER COLUMN idtypeutilisateur SET DEFAULT nextval('typeutilisateur_idtypeutilisateur_seq'::regclass);


--
-- Name: idutilisateur; Type: DEFAULT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY user ALTER COLUMN idutilisateur SET DEFAULT nextval('utilisateur_idutilisateur_seq'::regclass);


--
-- Name: pk_achat; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY achat
    ADD CONSTRAINT pk_achat PRIMARY KEY (numeroachat);


--
-- Name: pk_categorie; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT pk_categorie PRIMARY KEY (idcategorie);


--
-- Name: pk_client; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT pk_client PRIMARY KEY (idclient);


--
-- Name: pk_creditclient; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY creditclient
    ADD CONSTRAINT pk_creditclient PRIMARY KEY (idcreditclient);


--
-- Name: pk_creditfournisseur; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY creditfournisseur
    ADD CONSTRAINT pk_creditfournisseur PRIMARY KEY (idcreditfournisseur);


--
-- Name: pk_depense; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT pk_depense PRIMARY KEY (iddepense);


--
-- Name: pk_etat; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY etat
    ADD CONSTRAINT pk_etat PRIMARY KEY (idetat);


--
-- Name: pk_fournisseur; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY fournisseur
    ADD CONSTRAINT pk_fournisseur PRIMARY KEY (idfournisseur);


--
-- Name: pk_lignecreditachat; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY lignecreditachat
    ADD CONSTRAINT pk_lignecreditachat PRIMARY KEY (idcreditfournisseur, idligneachat);


--
-- Name: pk_lignecreditvente; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY lignecreditvente
    ADD CONSTRAINT pk_lignecreditvente PRIMARY KEY (idcreditclient, idlignecredit);


--
-- Name: pk_lignedachat; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY lignedachat
    ADD CONSTRAINT pk_lignedachat PRIMARY KEY (numeroachat, idligneachat);


--
-- Name: pk_lignedevente; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY lignedevente
    ADD CONSTRAINT pk_lignedevente PRIMARY KEY (numerovente, idlignevente);


--
-- Name: pk_motdepasse; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY motdepasse
    ADD CONSTRAINT pk_motdepasse PRIMARY KEY (idmdp);


--
-- Name: pk_parametres; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY parameters
    ADD CONSTRAINT pk_parametres PRIMARY KEY (dureeviemdp);


--
-- Name: pk_payementcreditclient; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY payementcreditclient
    ADD CONSTRAINT pk_payementcreditclient PRIMARY KEY (idcreditclient, idsession);


--
-- Name: pk_payementcreditfournisseur; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY payementcreditfournisseur
    ADD CONSTRAINT pk_payementcreditfournisseur PRIMARY KEY (idcreditfournisseur, idsession);


--
-- Name: pk_session; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY session
    ADD CONSTRAINT pk_session PRIMARY KEY (idsession);


--
-- Name: pk_typeutilisateur; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY typeutilisateur
    ADD CONSTRAINT pk_typeutilisateur PRIMARY KEY (idtypeutilisateur);


--
-- Name: pk_utilisateur; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY user
    ADD CONSTRAINT pk_utilisateur PRIMARY KEY (idutilisateur);


--
-- Name: pk_vente; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY vente
    ADD CONSTRAINT pk_vente PRIMARY KEY (numerovente);


--
-- Name: u_categorie; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT u_categorie UNIQUE (designation);


--
-- Name: u_client; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT u_client UNIQUE (telephoneclient);


--
-- Name: u_fournisseur; Type: CONSTRAINT; Schema: public; Owner: zerbo; Tablespace: 
--

ALTER TABLE ONLY fournisseur
    ADD CONSTRAINT u_fournisseur UNIQUE (telephonefournisseur);


--
-- Name: association11_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association11_fk ON achat USING btree (idsession);


--
-- Name: association19_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association19_fk ON payementcreditclient USING btree (idcreditclient);


--
-- Name: association19_fk2; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association19_fk2 ON payementcreditclient USING btree (idsession);


--
-- Name: association1_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association1_fk ON session USING btree (idutilisateur);


--
-- Name: association20_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association20_fk ON payementcreditfournisseur USING btree (idcreditfournisseur);


--
-- Name: association20_fk2; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association20_fk2 ON payementcreditfournisseur USING btree (idsession);


--
-- Name: association23_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association23_fk ON creditfournisseur USING btree (idfournisseur);


--
-- Name: association25_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association25_fk ON creditclient USING btree (idclient);


--
-- Name: association26_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association26_fk ON vente USING btree (idclient);


--
-- Name: association27_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association27_fk ON creditfournisseur USING btree (idetat);


--
-- Name: association28_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association28_fk ON creditclient USING btree (idetat);


--
-- Name: association29_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association29_fk ON vente USING btree (idetat);


--
-- Name: association2_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association2_fk ON motdepasse USING btree (idutilisateur);


--
-- Name: association30_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association30_fk ON achat USING btree (idetat);


--
-- Name: association32_fk3; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association32_fk3 ON depense USING btree (idsession);


--
-- Name: association33_fk3; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association33_fk3 ON lignecreditachat USING btree (idcreditfournisseur);


--
-- Name: association34_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association34_fk ON lignecreditvente USING btree (idcreditclient);


--
-- Name: association35_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association35_fk ON lignecreditvente USING btree (idcategorie);


--
-- Name: association36_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association36_fk ON lignecreditachat USING btree (idcategorie);


--
-- Name: association3_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association3_fk ON user USING btree (idtypeutilisateur);


--
-- Name: association4_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association4_fk ON lignedevente USING btree (numerovente);


--
-- Name: association5_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association5_fk ON lignedachat USING btree (numeroachat);


--
-- Name: association8_fk; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX association8_fk ON vente USING btree (idsession);


--
-- Name: fki_categorie; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_categorie ON lignecreditachat USING btree (idcategorie);


--
-- Name: fki_session; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session ON vente USING btree (idsession);


--
-- Name: fki_session2; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session2 ON payementcreditfournisseur USING btree (idsession);


--
-- Name: fki_session3; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session3 ON payementcreditclient USING btree (idsession);


--
-- Name: fki_session4; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session4 ON depense USING btree (idsession);


--
-- Name: fki_session5; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session5 ON creditfournisseur USING btree (idsession);


--
-- Name: fki_session6; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session6 ON creditclient USING btree (idsession);


--
-- Name: fki_session7; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_session7 ON achat USING btree (idsession);


--
-- Name: fki_typeutilisateur; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_typeutilisateur ON user USING btree (idtypeutilisateur);


--
-- Name: fki_utilisateur; Type: INDEX; Schema: public; Owner: zerbo; Tablespace: 
--

CREATE INDEX fki_utilisateur ON session USING btree (idutilisateur);


--
-- Name: fk_achat_associati_etat; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY achat
    ADD CONSTRAINT fk_achat_associati_etat FOREIGN KEY (idetat) REFERENCES etat(idetat) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_achat_associati_fourniss; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY achat
    ADD CONSTRAINT fk_achat_associati_fourniss FOREIGN KEY (idfournisseur) REFERENCES fournisseur(idfournisseur) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_categorie; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignecreditachat
    ADD CONSTRAINT fk_categorie FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- Name: fk_categorie1; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignecreditvente
    ADD CONSTRAINT fk_categorie1 FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- Name: fk_categorie2; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignedevente
    ADD CONSTRAINT fk_categorie2 FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- Name: fk_categorie3; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignedachat
    ADD CONSTRAINT fk_categorie3 FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- Name: fk_creditcl_associati_client; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY creditclient
    ADD CONSTRAINT fk_creditcl_associati_client FOREIGN KEY (idclient) REFERENCES client(idclient) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_creditcl_associati_etat; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY creditclient
    ADD CONSTRAINT fk_creditcl_associati_etat FOREIGN KEY (idetat) REFERENCES etat(idetat) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_creditfo_associati_etat; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY creditfournisseur
    ADD CONSTRAINT fk_creditfo_associati_etat FOREIGN KEY (idetat) REFERENCES etat(idetat) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_creditfo_associati_fourniss; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY creditfournisseur
    ADD CONSTRAINT fk_creditfo_associati_fourniss FOREIGN KEY (idfournisseur) REFERENCES fournisseur(idfournisseur) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_lignecre_associati_creditcl; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignecreditvente
    ADD CONSTRAINT fk_lignecre_associati_creditcl FOREIGN KEY (idcreditclient) REFERENCES creditclient(idcreditclient) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_lignecre_associati_creditfo; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignecreditachat
    ADD CONSTRAINT fk_lignecre_associati_creditfo FOREIGN KEY (idcreditfournisseur) REFERENCES creditfournisseur(idcreditfournisseur) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_lignedac_associati_achat; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignedachat
    ADD CONSTRAINT fk_lignedac_associati_achat FOREIGN KEY (numeroachat) REFERENCES achat(numeroachat) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_lignedev_associati_vente; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY lignedevente
    ADD CONSTRAINT fk_lignedev_associati_vente FOREIGN KEY (numerovente) REFERENCES vente(numerovente) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_payement_paiementc_creditcl; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY payementcreditclient
    ADD CONSTRAINT fk_payement_paiementc_creditcl FOREIGN KEY (idcreditclient) REFERENCES creditclient(idcreditclient) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_payement_paiementf_creditfo; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY payementcreditfournisseur
    ADD CONSTRAINT fk_payement_paiementf_creditfo FOREIGN KEY (idcreditfournisseur) REFERENCES creditfournisseur(idcreditfournisseur) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY vente
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY payementcreditfournisseur
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY payementcreditclient
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY creditfournisseur
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY creditclient
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_session; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY achat
    ADD CONSTRAINT fk_session FOREIGN KEY (idsession) REFERENCES session(idsession) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: fk_typeutilisateur; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY user
    ADD CONSTRAINT fk_typeutilisateur FOREIGN KEY (idtypeutilisateur) REFERENCES typeutilisateur(idtypeutilisateur) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_utilisateur; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY session
    ADD CONSTRAINT fk_utilisateur FOREIGN KEY (idutilisateur) REFERENCES user(idutilisateur) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_vente_associati_client; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY vente
    ADD CONSTRAINT fk_vente_associati_client FOREIGN KEY (idclient) REFERENCES client(idclient) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fk_vente_associati_etat; Type: FK CONSTRAINT; Schema: public; Owner: zerbo
--

ALTER TABLE ONLY vente
    ADD CONSTRAINT fk_vente_associati_etat FOREIGN KEY (idetat) REFERENCES etat(idetat) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

